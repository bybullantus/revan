package com.bc.revan.Entegration.Football;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bc.revan.DataAccess.ITeamDal;
import com.bc.revan.DataAccess.IVenueDal;
import com.bc.revan.Entegration.ITeamScheduledService;
import com.bc.revan.Entities.Team;
import com.bc.revan.Entities.Venue;
import com.bc.revan.Entities.Base.BaseResponse;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.transaction.Transactional;
import lombok.Data;

@Service
@Component
public class TeamScheduledService extends BaseRequest implements ITeamScheduledService {

	private ITeamDal teamDal;
	private IVenueDal venueDal;

	@Autowired
	public TeamScheduledService(RestTemplate restTemplate, ITeamDal teamDal, IVenueDal venueDal) {
		this.restTemplate = restTemplate;
		this.teamDal = teamDal;
		this.venueDal = venueDal;
	}

	@Transactional
	@Override
	public BaseResponse<TeamData> getTeams() {

		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", "application/json");
		headers.add("x-rapidapi-host", user);
		headers.add("x-rapidapi-key", password);

		List<String> countries = new ArrayList<String>();
		countries.add("Turkey");
		countries.add("England");
		countries.add("Italy");
		countries.add("Belgium");
		countries.add("Spain");
		countries.add("Netherlands");
		countries.add("France");
		countries.add("Germany");
		
		ResponseEntity<BaseResponse<TeamData>> exchange = null;
		for (String country : countries) {

			String url = apiUrl + "teams?country={country}";
			Map<String, String> params = Collections.singletonMap("country",country);

			HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
			exchange = restTemplate.exchange(url, HttpMethod.GET, entity,
					new ParameterizedTypeReference<BaseResponse<TeamData>>() {
					}, params);

			for (TeamData responseItem : exchange.getBody().getResponseList()) {

				Team team = responseItem.getTeam();
				Venue varVenue;
				try {
					varVenue = venueDal.getById(responseItem.getVenue().getId());
					if (varVenue == null)
						varVenue = venueDal.add(responseItem.getVenue());
				} catch (Exception e) {
					varVenue = null;
				}

				team.setVenue(varVenue);
				if (teamDal.getById(team.getId())!=null) {
					continue;
				}
				team = teamDal.add(team);
			}

		}
		return exchange.getBody();
	}

	@Data
	private static class TeamData {

		@JsonProperty("team")
		private Team team;
		@JsonProperty("venue")
		private Venue venue;

	}

}
