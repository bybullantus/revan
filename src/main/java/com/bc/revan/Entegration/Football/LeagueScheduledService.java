package com.bc.revan.Entegration.Football;


import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bc.revan.DataAccess.ICountryDal;
import com.bc.revan.DataAccess.ILeagueDal;
import com.bc.revan.Entegration.ILeagueScheduledService;
import com.bc.revan.Entities.Country;
import com.bc.revan.Entities.League;
import com.bc.revan.Entities.Season;
import com.bc.revan.Entities.Base.BaseResponse;

import jakarta.transaction.Transactional;
import lombok.Data;

@Service
@Component
public class LeagueScheduledService extends BaseRequest implements ILeagueScheduledService {

	private ILeagueDal leagueDal;
	private ICountryDal countryDal;
	
	@Autowired
	public LeagueScheduledService(RestTemplate restTemplate, ILeagueDal leagueDal, ICountryDal countryDal) {
		this.restTemplate = restTemplate;
		this.leagueDal = leagueDal;
		this.countryDal = countryDal;
	}

	@Override
	@Transactional
	public   BaseResponse<LeagueData> getLeagues() throws InterruptedException, ExecutionException   {
		HttpHeaders headers = new HttpHeaders();

		headers.add("x-rapidapi-host", user);
		headers.add("x-rapidapi-key", password);

		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

		ResponseEntity<BaseResponse<LeagueData>> exchange = restTemplate.exchange(apiUrl + "leagues", HttpMethod.GET,
				entity, new ParameterizedTypeReference<BaseResponse<LeagueData>>() {
				});

		for (LeagueData responseItem : exchange.getBody().getResponseList()) {
				
			League league = responseItem.getLeague();
			
			if(leagueDal.getById(league.getId()) == null) {
			
			Country varCountry = countryDal.getByName(responseItem.getCountry().getName());
			if (varCountry == null)
				varCountry = countryDal.add(Country.builder().name(responseItem.getCountry().getName())
						.code(responseItem.getCountry().getCode()).flag(responseItem.getCountry().getFlag()).build());

			league.setCountry(varCountry);
			league.setSeasons(responseItem.getSeasons());
			league = leagueDal.add(league);
			}
		}

		return exchange.getBody();
	}

	@Data
	private static class LeagueData {

		private League league;

		private Country country;

		private ArrayList<Season> seasons;
		


	}

}
