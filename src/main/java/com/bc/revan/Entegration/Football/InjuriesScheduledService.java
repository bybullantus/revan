package com.bc.revan.Entegration.Football;

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

import com.bc.revan.DataAccess.IFixtureDal;
import com.bc.revan.DataAccess.IInjuriesDal;
import com.bc.revan.DataAccess.ILeagueDal;
import com.bc.revan.DataAccess.IPlayerDal;
import com.bc.revan.DataAccess.ISeasonDal;
import com.bc.revan.DataAccess.ITeamDal;
import com.bc.revan.Entegration.IInjuriesScheduledService;
import com.bc.revan.Entities.Fixture;
import com.bc.revan.Entities.Injuries;
import com.bc.revan.Entities.League;
import com.bc.revan.Entities.Player;
import com.bc.revan.Entities.Team;
import com.bc.revan.Entities.Base.BaseResponse;

import jakarta.transaction.Transactional;
import lombok.Data;

@Service
@Component
public class InjuriesScheduledService extends BaseRequest implements IInjuriesScheduledService {

	@Autowired
	IInjuriesDal injuriesDal;
	@Autowired
	IPlayerDal playerDal;
	@Autowired
	ITeamDal teamDal;
	@Autowired
	IFixtureDal fixtureDal;
	@Autowired
	ILeagueDal leagueDal;	
	@Autowired
    ISeasonDal seasonDal;

	public InjuriesScheduledService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Transactional
	@Override
	public BaseResponse getInjuries() throws InterruptedException, ExecutionException {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", "application/json");
		headers.add("x-rapidapi-host", user);
		headers.add("x-rapidapi-key", password);

		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		long fixtureId;
		for (Fixture responseFixture : fixtureDal.getAll()) {
			fixtureId = responseFixture.getId();
			if (injuriesDal.getByFixture(fixtureId) == null) {
				String url = apiUrl + "/injuries?fixture=" + fixtureId;

				ResponseEntity<BaseResponse<InjuriesFixtureData>> exchange = restTemplate.exchange(url, HttpMethod.GET,
						entity, new ParameterizedTypeReference<BaseResponse<InjuriesFixtureData>>() {
						});
				System.out.println(exchange.getBody());

				for (InjuriesFixtureData responseItem : exchange.getBody().getResponseList()) {

					Player player = playerDal.getById(responseItem.getPlayer().getId());
					if (player == null && responseItem.getPlayer().getId() != 0) {
						player = player.builder().id(responseItem.getPlayer().getId())
								.name(responseItem.getPlayer().getName())
								.photo(responseItem.getPlayer().getPhoto()).build();
						player = playerDal.add(player);
					}

					Team team = teamDal.getById(responseItem.getTeam().getId());

					Fixture fixture = fixtureDal.getById(fixtureId);

					League league = leagueDal.getById(responseItem.getLeague().getId());

					Injuries injuries = Injuries.builder().player(player).team(team).fixture(fixture).league(league)
							.type(responseItem.getPlayer().getType()).reason(responseItem.getPlayer().getReason())
							.season(seasonDal.getByLeagueAndYear(league, responseItem.getLeague().getSeason())).build();
					injuries = injuriesDal.add(injuries);

				
				}
			}
		}
		return null;
	}

	@Data
	private static class InjuriesFixtureData {

		PlayerData player;
		TeamData team;
		FixtureData fixture;
		LeagueData league;

		@Data
		private static class FixtureData {
			long id;
			String timezone;
			String date;
			long timestamp;

		}

		@Data
		private static class TeamData {

			long id;
			String name;
			String logo;

		}

		@Data
		private static class PlayerData {

			long id;
			String name;
			String photo;
			String type;
			String reason;

		}

		@Data
		private static class LeagueData {

			long id;
			int season;
			String name;
			String country;
			String logo;
			String flag;

		}

	}

}
