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

import com.bc.revan.DataAccess.ICardsOfStatisticsForPlayersDal;
import com.bc.revan.DataAccess.IDribblesOfStatisticsForPlayersDal;
import com.bc.revan.DataAccess.IDuelsOfStatisticsForPlayersDal;
import com.bc.revan.DataAccess.IFixtureDal;
import com.bc.revan.DataAccess.IFoulsOfStatisticsForPlayersDal;
import com.bc.revan.DataAccess.IGamesOfStatisticsForPlayersDal;
import com.bc.revan.DataAccess.IGoalsOfStatisticsForPlayersDal;
import com.bc.revan.DataAccess.IMainOfPlayersForFixtureDal;
import com.bc.revan.DataAccess.IPassesOfStatisticsForPlayersDal;
import com.bc.revan.DataAccess.IPenaltyOfStatisticsForPlayersDal;
import com.bc.revan.DataAccess.IPlayerDal;
import com.bc.revan.DataAccess.IPlayersForFixtureDal;
import com.bc.revan.DataAccess.IShotsOfStatisticsForPlayersDal;
import com.bc.revan.DataAccess.IStatisticsOfPlayersForFixtureDal;
import com.bc.revan.DataAccess.ITacklesOfStatisticsForPlayersDal;
import com.bc.revan.DataAccess.ITeamDal;
import com.bc.revan.Entegration.IPlayersForFixtureScheduledService;
import com.bc.revan.Entities.CardsOfStatisticsForPlayers;
import com.bc.revan.Entities.DribblesOfStatisticsForPlayers;
import com.bc.revan.Entities.DuelsOfStatisticsForPlayers;
import com.bc.revan.Entities.Fixture;
import com.bc.revan.Entities.FoulsOfStatisticsForPlayers;
import com.bc.revan.Entities.GamesOfStatisticsForPlayers;
import com.bc.revan.Entities.GoalsOfStatisticsForPlayers;
import com.bc.revan.Entities.MainOfPlayersForFixture;
import com.bc.revan.Entities.PassesOfStatisticsForPlayers;
import com.bc.revan.Entities.PenaltyOfStatisticsForPlayers;
import com.bc.revan.Entities.Player;
import com.bc.revan.Entities.PlayersForFixture;
import com.bc.revan.Entities.ShotsOfStatisticsForPlayers;
import com.bc.revan.Entities.StatisticsOfPlayersForFixture;
import com.bc.revan.Entities.TacklesOfStatisticsForPlayers;
import com.bc.revan.Entities.Team;
import com.bc.revan.Entities.Base.BaseResponse;
import com.bc.revan.Entities.Dto.PlayersForFixtureScheduledDtos.PlayersForFixtureScheduledDto;
import com.bc.revan.Entities.Dto.PlayersForFixtureScheduledDtos.StatisticsOfPlayersForFixtureScheduledDto;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.transaction.Transactional;
import lombok.Data;

@Service
@Component
public class PlayersForFixtureScheduledService extends BaseRequest implements IPlayersForFixtureScheduledService {

	@Autowired
	IMainOfPlayersForFixtureDal mainOfPlayersForFixtureDal;

	@Autowired
	IPlayersForFixtureDal playersForFixtureDal;

	@Autowired
	IStatisticsOfPlayersForFixtureDal statisticsOfPlayersForFixtureDal;

	@Autowired
	IGamesOfStatisticsForPlayersDal gamesOfStatisticsForPlayersDal;

	@Autowired
	IShotsOfStatisticsForPlayersDal shotsOfStatisticsForPlayersDal;

	@Autowired
	IGoalsOfStatisticsForPlayersDal goalsOfStatisticsForPlayersDal;

	@Autowired
	IPassesOfStatisticsForPlayersDal passesOfStatisticsForPlayersDal;

	@Autowired
	ITacklesOfStatisticsForPlayersDal tacklesOfStatisticsForPlayersDal;

	@Autowired
	IDuelsOfStatisticsForPlayersDal duelsOfStatisticsForPlayersDal;

	@Autowired
	IDribblesOfStatisticsForPlayersDal dribblesOfStatisticsForPlayersDal;

	@Autowired
	IFoulsOfStatisticsForPlayersDal foulsOfStatisticsForPlayersDal;

	@Autowired
	ICardsOfStatisticsForPlayersDal cardsOfStatisticsForPlayersDal;

	@Autowired
	IPenaltyOfStatisticsForPlayersDal penaltyOfStatisticsForPlayersDal;

	@Autowired
	IFixtureDal fixtureDal;

	@Autowired
	ITeamDal teamDal;

	@Autowired
	IPlayerDal playerDal;

	public PlayersForFixtureScheduledService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Transactional
	@Override
	public BaseResponse getPlayers() throws InterruptedException, ExecutionException {

		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", "application/json");
		headers.add("x-rapidapi-host", user);
		headers.add("x-rapidapi-key", password);

		long fixtureId;

		for (Fixture responseFixture : fixtureDal.getAll()) {

			fixtureId = responseFixture.getId();
			
			if(mainOfPlayersForFixtureDal.getByFixture(fixtureId)!=null)
			{
				continue;
			}
			HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

			String url = apiUrl + "/fixtures/players?fixture=" + fixtureId;

			ResponseEntity<BaseResponse<PlayerForFixtureData>> exchange = restTemplate.exchange(url, HttpMethod.GET,
					entity, new ParameterizedTypeReference<BaseResponse<PlayerForFixtureData>>() {
					});

			for (PlayerForFixtureData responseItem : exchange.getBody().getResponseList()) {

				List<PlayersForFixture> playerList = new ArrayList<PlayersForFixture>();

				for (PlayersForFixtureScheduledDto responsePlayers : responseItem.getPlayers()) {
					Player player = playerDal.getById(responsePlayers.getPlayer().getId());
					if (player == null) {
						player = Player.builder().id(responsePlayers.getPlayer().getId())
								.name(responsePlayers.getPlayer().getName())
								.photo(responsePlayers.getPlayer().getPhoto()).build();
						player = playerDal.add(player);
					}

					List<StatisticsOfPlayersForFixture> statisticsList = new ArrayList<StatisticsOfPlayersForFixture>();
					for (StatisticsOfPlayersForFixtureScheduledDto playersForFixture : responsePlayers
							.getStatistics()) {

						GamesOfStatisticsForPlayers games = GamesOfStatisticsForPlayers.builder()
								.minutes(playersForFixture.getGames().getMinutes())
								.number(playersForFixture.getGames().getNumber())
								.position(playersForFixture.getGames().getPosition())
								.rating(playersForFixture.getGames().getRating())
								.captain(playersForFixture.getGames().isCaptain())
								.substitute(playersForFixture.getGames().isSubstitute()).build();
						games = gamesOfStatisticsForPlayersDal.add(games);

						ShotsOfStatisticsForPlayers shots = ShotsOfStatisticsForPlayers.builder()
								.total(playersForFixture.getShots().getTotal()).on(playersForFixture.getShots().getOn())
								.build();
						shots = shotsOfStatisticsForPlayersDal.add(shots);

						GoalsOfStatisticsForPlayers goals = GoalsOfStatisticsForPlayers.builder()
								.total(playersForFixture.getGoals().getTotal())
								.conceded(playersForFixture.getGoals().getConceded())
								.assists(playersForFixture.getGoals().getAssists())
								.saves(playersForFixture.getGoals().getSaves()).build();
						goals = goalsOfStatisticsForPlayersDal.add(goals);

						PassesOfStatisticsForPlayers passes = PassesOfStatisticsForPlayers.builder()
								.total(playersForFixture.getPasses().getTotal())
								.key(playersForFixture.getPasses().getKey())
								.accuracy(playersForFixture.getPasses().getAccuracy()).build();
						passes = passesOfStatisticsForPlayersDal.add(passes);

						TacklesOfStatisticsForPlayers tackles = TacklesOfStatisticsForPlayers.builder()
								.total(playersForFixture.getTackles().getTotal())
								.blocks(playersForFixture.getTackles().getBlocks())
								.interceptions(playersForFixture.getTackles().getInterceptions()).build();
						tackles = tacklesOfStatisticsForPlayersDal.add(tackles);

						DuelsOfStatisticsForPlayers duels = DuelsOfStatisticsForPlayers.builder()
								.total(playersForFixture.getDuels().getTotal())
								.won(playersForFixture.getDuels().getWon()).build();
						duels = duelsOfStatisticsForPlayersDal.add(duels);

						DribblesOfStatisticsForPlayers dribble = DribblesOfStatisticsForPlayers.builder()
								.attempts(playersForFixture.getDribbles().getAttempts())
								.success(playersForFixture.getDribbles().getSuccess())
								.past(playersForFixture.getDribbles().getPast()).build();
						dribble = dribblesOfStatisticsForPlayersDal.add(dribble);

						FoulsOfStatisticsForPlayers fouls = FoulsOfStatisticsForPlayers.builder()
								.drawn(playersForFixture.getFouls().getDrawn())
								.committed(playersForFixture.getFouls().getCommitted()).build();
						fouls = foulsOfStatisticsForPlayersDal.add(fouls);

						CardsOfStatisticsForPlayers cards = CardsOfStatisticsForPlayers.builder()
								.yellow(playersForFixture.getCards().getYellow())
								.red(playersForFixture.getCards().getRed()).build();
						cards = cardsOfStatisticsForPlayersDal.add(cards);

						PenaltyOfStatisticsForPlayers penalty = PenaltyOfStatisticsForPlayers.builder()
								.won(playersForFixture.getPenalty().getWon())
								.commited(playersForFixture.getPenalty().getCommited())
								.scored(playersForFixture.getPenalty().getScored())
								.missed(playersForFixture.getPenalty().getMissed())
								.saved(playersForFixture.getPenalty().getSaved()).build();
						penalty = penaltyOfStatisticsForPlayersDal.add(penalty);

						StatisticsOfPlayersForFixture statistics = StatisticsOfPlayersForFixture.builder().games(games)
								.offsides(playersForFixture.getOffsides()).shots(shots).goals(goals).passes(passes)
								.tackles(tackles).duels(duels).dribbles(dribble).fouls(fouls).cards(cards)
								.penalty(penalty).build();
						statistics = statisticsOfPlayersForFixtureDal.add(statistics);
						statisticsList.add(statistics);
					}
					PlayersForFixture players = PlayersForFixture.builder().player(player).statistics(statisticsList)
							.build();
					players = playersForFixtureDal.add(players);
					playerList.add(players);
				}

				Fixture fixture = fixtureDal.getById(fixtureId);

				Team team = teamDal.getById(responseItem.getTeam().getId());

				MainOfPlayersForFixture main = MainOfPlayersForFixture.builder().team(team).fixture(fixture)
						.players(playerList).build();
				main = mainOfPlayersForFixtureDal.add(main);
			}
		}
		return null;
	}

	@Data
	private static class PlayerForFixtureData {

		TeamData team;

		List<PlayersForFixtureScheduledDto> players;

		@Data
		private static class TeamData {

			long id;
			String name;
			String logo;
			String update;
		}

	}

}
