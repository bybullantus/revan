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

import com.bc.revan.DataAccess.IEventsForFixtureDal;
import com.bc.revan.DataAccess.IFixtureDal;
import com.bc.revan.DataAccess.IPlayerDal;
import com.bc.revan.DataAccess.IScoreOfFixtureDal;
import com.bc.revan.DataAccess.ITeamDal;
import com.bc.revan.DataAccess.ITimeOfEventDal;
import com.bc.revan.Entegration.IEventsForFixtureScheduledService;
import com.bc.revan.Entities.EventsForFixture;
import com.bc.revan.Entities.Fixture;
import com.bc.revan.Entities.Player;
import com.bc.revan.Entities.Team;
import com.bc.revan.Entities.TimeOfEvent;
import com.bc.revan.Entities.Base.BaseResponse;

import jakarta.transaction.Transactional;
import lombok.Data;

@Service
@Component
public class EventsForFixtureScheduledService extends BaseRequest implements IEventsForFixtureScheduledService {

	@Autowired
	ITimeOfEventDal timeOfEventDal;

	@Autowired
	ITeamDal teamDal;

	@Autowired
	IPlayerDal playerDal;

	@Autowired
	IEventsForFixtureDal eventsForFixtureDal;

	@Autowired
	IFixtureDal fixtureDal;

	public EventsForFixtureScheduledService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Transactional
	@Override
	public BaseResponse getEventsForFixture() throws InterruptedException, ExecutionException {

		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", "application/json");
		headers.add("x-rapidapi-host", user);
		headers.add("x-rapidapi-key", password);

		// long fixtureId = 884670;
		long fixtureId;
		for (Fixture responseFixture : fixtureDal.getAll()) {
			fixtureId = responseFixture.getId();
			if (eventsForFixtureDal.getByFixture(fixtureId)==null) {

				HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

				String url = apiUrl + "/fixtures/events?fixture=" + fixtureId;

				ResponseEntity<BaseResponse<EventsForFixtureData>> exchange = restTemplate.exchange(url, HttpMethod.GET,
						entity, new ParameterizedTypeReference<BaseResponse<EventsForFixtureData>>() {
						});
				System.out.println(exchange.getBody());
				for (EventsForFixtureData responseItem : exchange.getBody().getResponseList()) {
					TimeOfEvent time = TimeOfEvent.builder().elapsed(responseItem.getTime().getElapsed())
							.extra(responseItem.getTime().getExtra()).build();
					time = timeOfEventDal.add(time);

					Team team = teamDal.getById(responseItem.getTeam().getId());

					Player player = playerDal.getById(responseItem.getPlayer().getId());
					if (player == null && responseItem.getPlayer().getId() != 0) {
						player = player.builder().id(responseItem.getPlayer().getId()).name(responseItem.getPlayer().getName())
								.build();
						player = playerDal.add(player);
					}

					Player assist = playerDal.getById(responseItem.getAssist().getId());
					if (assist == null && responseItem.getAssist().getId() != 0) {
						assist = assist.builder().id(responseItem.getAssist().getId()).name(responseItem.getAssist().getName())
								.build();
						assist = playerDal.add(assist);
					}

					EventsForFixture event = EventsForFixture.builder().team(team).time(time).player(player)
							.assist(assist).type(responseItem.getType()).detail(responseItem.getDetail())
							.comments(responseItem.getComments()).fixture(responseFixture).build();

					event = eventsForFixtureDal.add(event);

				}
			}
		}

		return null;
	}

	@Data
	private static class EventsForFixtureData {

		TimeData time;
		TeamData team;
		PlayerData player;
		AssistData assist;
		String type;
		String detail;
		String comments;

		@Data
		private static class TimeData {

			int elapsed;
			Boolean extra;

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

		}

		@Data
		private static class AssistData {

			long id;
			String name;

		}

	}

}
