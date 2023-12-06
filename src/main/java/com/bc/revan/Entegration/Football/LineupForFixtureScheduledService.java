package com.bc.revan.Entegration.Football;

import java.util.ArrayList;
import java.util.List;
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

import com.bc.revan.DataAccess.ICoachsDal;
import com.bc.revan.DataAccess.IColorOfLineupDal;
import com.bc.revan.DataAccess.IFixtureDal;
import com.bc.revan.DataAccess.ILineupForFixtureDal;
import com.bc.revan.DataAccess.IPlayerDal;
import com.bc.revan.DataAccess.IStartXIAndSubsOfLineupDal;
import com.bc.revan.DataAccess.ITeamDal;
import com.bc.revan.Entegration.ILineupForFixtureScheduledService;
import com.bc.revan.Entities.Coachs;
import com.bc.revan.Entities.ColorOfLineup;
import com.bc.revan.Entities.Fixture;
import com.bc.revan.Entities.LineupForFixture;
import com.bc.revan.Entities.Player;
import com.bc.revan.Entities.StartXIAndSubsOfLineup;
import com.bc.revan.Entities.Team;
import com.bc.revan.Entities.Base.BaseResponse;
import com.bc.revan.Entities.Dto.LineupForFixtureScheduledDtos.PlayerOfLineupForFixtureScheduledDto;

import jakarta.transaction.Transactional;
import lombok.Data;

@Service
@Component
public class LineupForFixtureScheduledService extends BaseRequest implements ILineupForFixtureScheduledService {

	@Autowired
	ILineupForFixtureDal lineupForFixtureDal;
	@Autowired
	IColorOfLineupDal colorOfLineupDal;
	@Autowired
	IStartXIAndSubsOfLineupDal startXIAndSubsOfLineupDal;
	@Autowired
	ITeamDal teamDal;
	@Autowired
	ICoachsDal coachDal;
	@Autowired
	IPlayerDal playerDal;
	@Autowired
	IFixtureDal fixtureDal;

	public LineupForFixtureScheduledService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Override
	@Transactional
	public BaseResponse getLineups() throws InterruptedException, ExecutionException {

		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", "application/json");
		headers.add("x-rapidapi-host", user);
		headers.add("x-rapidapi-key", password);

		long fixtureId;

		for (Fixture responseFixture : fixtureDal.getAll()) {

			fixtureId = responseFixture.getId();

			HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

			String url = apiUrl + "/fixtures/lineups?fixture=" + fixtureId;

			ResponseEntity<BaseResponse<LineupForFixtureData>> exchange = restTemplate.exchange(url, HttpMethod.GET,
					entity, new ParameterizedTypeReference<BaseResponse<LineupForFixtureData>>() {
					});
			System.out.println(exchange.getBody());
			for (LineupForFixtureData responseItem : exchange.getBody().getResponseList()) {

				List<StartXIAndSubsOfLineup> startXI = new ArrayList<StartXIAndSubsOfLineup>();

				for (PlayerOfLineupForFixtureScheduledDto responsePlayer : responseItem.getStartXI()) {
					Player player = new Player();
					player = playerDal.getById(responsePlayer.getPlayer().getId());
					if (player == null) {
						player = Player.builder().id(responsePlayer.getPlayer().getId())
								.name(responsePlayer.getPlayer().getName()).build();
						player = playerDal.add(player);
					}

					StartXIAndSubsOfLineup elementOfStartXI = StartXIAndSubsOfLineup.builder().player(player)
							.position(responsePlayer.getPlayer().getPos())
							.number(responsePlayer.getPlayer().getNumber()).grid(responsePlayer.getPlayer().getGrid())
							.build();
					elementOfStartXI = startXIAndSubsOfLineupDal.add(elementOfStartXI);
					startXI.add(elementOfStartXI);
				}

				List<StartXIAndSubsOfLineup> substitutes = new ArrayList<StartXIAndSubsOfLineup>();

				for (PlayerOfLineupForFixtureScheduledDto responsePlayer : responseItem.getSubstitutes()) {
					Player player = new Player();
					player = playerDal.getById(responsePlayer.getPlayer().getId());
					if (player == null) {
						player = Player.builder().id(responsePlayer.getPlayer().getId())
								.name(responsePlayer.getPlayer().getName()).build();
						player = playerDal.add(player);
					}

					StartXIAndSubsOfLineup elementOfSubstitues = StartXIAndSubsOfLineup.builder().player(player)
							.position(responsePlayer.getPlayer().getPos())
							.number(responsePlayer.getPlayer().getNumber()).grid(responsePlayer.getPlayer().getGrid())
							.build();
					elementOfSubstitues = startXIAndSubsOfLineupDal.add(elementOfSubstitues);
					substitutes.add(elementOfSubstitues);
				}

				Team team = teamDal.getById(responseItem.getTeam().getId());
				Coachs coach = coachDal.getById(responseItem.getCoach().getId());
				ColorOfLineup playerColor = ColorOfLineup.builder()
						.primary(responseItem.getTeam().getColors().getPlayer().getPrimary())
						.number(responseItem.getTeam().getColors().getPlayer().getNumber())
						.border(responseItem.getTeam().getColors().getPlayer().getBorder()).build();
				playerColor = colorOfLineupDal.add(playerColor);
				ColorOfLineup goalKeeperColor = ColorOfLineup.builder()
						.primary(responseItem.getTeam().getColors().getGoalkeeper().getPrimary())
						.number(responseItem.getTeam().getColors().getGoalkeeper().getNumber())
						.border(responseItem.getTeam().getColors().getGoalkeeper().getBorder()).build();
				goalKeeperColor = colorOfLineupDal.add(goalKeeperColor);
				Fixture fixture = fixtureDal.getById(fixtureId);

				LineupForFixture lineup = LineupForFixture.builder().formation(responseItem.getFormation()).team(team)
						.coach(coach).colorsOfPlayer(playerColor).colorsOfGoalkeeper(goalKeeperColor).startXI(startXI)
						.substitutes(substitutes).fixture(fixture).build();
				lineup = lineupForFixtureDal.add(lineup);
			}
		}
		return null;
	}

	@Data
	private static class LineupForFixtureData {
		TeamData team;
		CoachData coach;
		String formation;
		List<PlayerOfLineupForFixtureScheduledDto> startXI;
		List<PlayerOfLineupForFixtureScheduledDto> substitutes;

		@Data
		private static class CoachData {

			long id;
			String name;
			String photo;

		}

		@Data
		private static class TeamData {

			long id;
			String name;
			String logo;
			ColorsData colors;

		}

		@Data
		private static class ColorsData {

			ColorsDetailData player;
			ColorsDetailData goalkeeper;

		}

		@Data
		private static class ColorsDetailData {

			String primary;
			String number;
			String border;

		}

	}

}
