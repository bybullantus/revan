package com.bc.revan.Entegration.Football;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
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

import com.bc.revan.DataAccess.IBirthDal;
import com.bc.revan.DataAccess.ICardsOfStatisticsForPlayersDal;
import com.bc.revan.DataAccess.IDribblesOfStatisticsForPlayersDal;
import com.bc.revan.DataAccess.IDuelsOfStatisticsForPlayersDal;
import com.bc.revan.DataAccess.IFoulsOfStatisticsForPlayersDal;
import com.bc.revan.DataAccess.IGamesForStatisticsOfPlayerDal;
import com.bc.revan.DataAccess.IGoalsOfStatisticsForPlayersDal;
import com.bc.revan.DataAccess.ILeagueDal;
import com.bc.revan.DataAccess.IPassesOfStatisticsForPlayersDal;
import com.bc.revan.DataAccess.IPenaltyOfStatisticsForPlayersDal;
import com.bc.revan.DataAccess.IPlayerDal;
import com.bc.revan.DataAccess.ISeasonDal;
import com.bc.revan.DataAccess.IShotsOfStatisticsForPlayersDal;
import com.bc.revan.DataAccess.IStatisticsOfPlayerDal;
import com.bc.revan.DataAccess.ISubstituteOfPlayerDal;
import com.bc.revan.DataAccess.ITacklesOfStatisticsForPlayersDal;
import com.bc.revan.DataAccess.ITeamDal;
import com.bc.revan.Entegration.IPlayersScheduledService;
import com.bc.revan.Entities.Birth;
import com.bc.revan.Entities.CardsOfStatisticsForPlayers;
import com.bc.revan.Entities.DribblesOfStatisticsForPlayers;
import com.bc.revan.Entities.DuelsOfStatisticsForPlayers;
import com.bc.revan.Entities.FoulsOfStatisticsForPlayers;
import com.bc.revan.Entities.GamesForStatisticsOfPlayer;
import com.bc.revan.Entities.GoalsOfStatisticsForPlayers;
import com.bc.revan.Entities.PassesOfStatisticsForPlayers;
import com.bc.revan.Entities.PenaltyOfStatisticsForPlayers;
import com.bc.revan.Entities.Player;
import com.bc.revan.Entities.ShotsOfStatisticsForPlayers;
import com.bc.revan.Entities.StatisticsOfPlayer;
import com.bc.revan.Entities.SubstituteOfPlayer;
import com.bc.revan.Entities.TacklesOfStatisticsForPlayers;
import com.bc.revan.Entities.Team;
import com.bc.revan.Entities.Base.BaseResponse;
import com.bc.revan.Entities.Dto.PlayersScheduledDtos.StatisticsOfPlayersScheduledDto;

import jakarta.transaction.Transactional;
import lombok.Data;

@Service
@Component
public class PlayersScheduledService extends BaseRequest implements IPlayersScheduledService {

	@Autowired
	IPlayerDal playerDal;
	@Autowired
	IBirthDal birthDal;
	@Autowired
	IStatisticsOfPlayerDal statisticsOfPlayerDal;
	@Autowired
	ITeamDal teamDal;
	@Autowired
	ILeagueDal leagueDal;
	@Autowired
	ISeasonDal seasonDal;
	@Autowired
	IGamesForStatisticsOfPlayerDal gamesForStatisticsOfPlayerDal;
	@Autowired
	ISubstituteOfPlayerDal substituteOfPlayerDal;
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

	
	public PlayersScheduledService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Transactional
	@Override
	public BaseResponse getPlayers() throws InterruptedException, ExecutionException {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", "application/json");
		headers.add("x-rapidapi-host", user);
		headers.add("x-rapidapi-key", password);

		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		
		
		//for (Player playerItem :playerDal.getAll()) {
		
			long id= 216; //playerItem.getId();
			long season= 2022 ;//LocalDate.now().getYear();
			
			String url = apiUrl + "/players?id=" + id+"&season="+ season;

			ResponseEntity<BaseResponse<PlayersData>> exchange = restTemplate.exchange(url, HttpMethod.GET,
					entity, new ParameterizedTypeReference<BaseResponse<PlayersData>>() {
					});
			System.out.println(exchange.getBody());
			
			List<StatisticsOfPlayer> statisticList= new ArrayList<StatisticsOfPlayer>();
			
			Player dummyPlayer=playerDal.getById(exchange.getBody().getResponseList().get(0).getPlayer().id);
			if(dummyPlayer!=null)
				statisticList=dummyPlayer.getStatistics();
			
			for (StatisticsOfPlayersScheduledDto statisticsItem : exchange.getBody().getResponseList().get(0).getStatistics()) {
				
				GamesForStatisticsOfPlayer game=GamesForStatisticsOfPlayer.builder()
						.appearences(statisticsItem.getGames().getAppearences())
						.lineups(statisticsItem.getGames().getLineups())
						.minutes(statisticsItem.getGames().getMinutes())
						.number(statisticsItem.getGames().getNumber())
						.position(statisticsItem.getGames().getPosition())
						.rating(statisticsItem.getGames().getRating())
						.captain(statisticsItem.getGames().isCaptain()).build();
				game=gamesForStatisticsOfPlayerDal.add(game);
				
				SubstituteOfPlayer substitute=SubstituteOfPlayer.builder()
						.in(statisticsItem.getSubstitutes().getIn())
						.out(statisticsItem.getSubstitutes().getOut())
						.bench(statisticsItem.getSubstitutes().getBench()).build();		
				substitute=substituteOfPlayerDal.add(substitute);
				
				ShotsOfStatisticsForPlayers shots=ShotsOfStatisticsForPlayers.builder().total(statisticsItem.getShots().getTotal())
						.on(statisticsItem.getShots().getOn()).build();
				shots=shotsOfStatisticsForPlayersDal.add(shots);
				
				GoalsOfStatisticsForPlayers goals=GoalsOfStatisticsForPlayers.builder().total(statisticsItem.getGoals().getTotal())
						.conceded(statisticsItem.getGoals().getConceded())
						.assists(statisticsItem.getGoals().getAssists())
						.saves(statisticsItem.getGoals().getSaves()).build();
				goals=goalsOfStatisticsForPlayersDal.add(goals);
				
				PassesOfStatisticsForPlayers passes=PassesOfStatisticsForPlayers.builder()
						.total(statisticsItem.getPasses().getTotal())
						.key(statisticsItem.getPasses().getKey())
						.accuracy(statisticsItem.getPasses().getAccuracy()).build();
				passes=passesOfStatisticsForPlayersDal.add(passes);
				
				TacklesOfStatisticsForPlayers tackles=TacklesOfStatisticsForPlayers.builder()
						.total(statisticsItem.getTackles().getTotal())
						.blocks(statisticsItem.getTackles().getBlocks())
						.interceptions(statisticsItem.getTackles().getInterceptions()).build();
				tackles=tacklesOfStatisticsForPlayersDal.add(tackles);
				
				DuelsOfStatisticsForPlayers duels=DuelsOfStatisticsForPlayers.builder().total(statisticsItem.getDuels().getTotal())
						.won(statisticsItem.getDuels().getWon()).build();
				duels=duelsOfStatisticsForPlayersDal.add(duels);
				
				DribblesOfStatisticsForPlayers dribbles= DribblesOfStatisticsForPlayers.builder()
						.attempts(statisticsItem.getDribbles().getAttempts())
						.success(statisticsItem.getDribbles().getSuccess())
						.past(statisticsItem.getDribbles().getPast()).build();
				dribbles=dribblesOfStatisticsForPlayersDal.add(dribbles);
				
				FoulsOfStatisticsForPlayers fouls=FoulsOfStatisticsForPlayers.builder()
						.drawn(statisticsItem.getFouls().getDrawn())
						.committed(statisticsItem.getFouls().getCommitted()).build();
				fouls=foulsOfStatisticsForPlayersDal.add(fouls);
				
				CardsOfStatisticsForPlayers cards=CardsOfStatisticsForPlayers.builder()
						.yellow(statisticsItem.getCards().getYellow())
						.yellowred(statisticsItem.getCards().getYellowred())
						.red(statisticsItem.getCards().getRed()).build();
				cards=cardsOfStatisticsForPlayersDal.add(cards);
				
				PenaltyOfStatisticsForPlayers penalty = PenaltyOfStatisticsForPlayers.builder()
						.won(statisticsItem.getPenalty().getWon())
						.commited(statisticsItem.getPenalty().getCommited())
						.scored(statisticsItem.getPenalty().getScored())
						.missed(statisticsItem.getPenalty().getMissed())
						.saved(statisticsItem.getPenalty().getSaved())
						.build();
				penalty=penaltyOfStatisticsForPlayersDal.add(penalty);
				
				
				
				Team team=teamDal.getById(statisticsItem.getTeam().getId());
				
				StatisticsOfPlayer statistic=StatisticsOfPlayer.builder()
						.team(team==null?null:team)
						.season(seasonDal.getByLeagueAndYear(leagueDal.getById(statisticsItem.getLeague().getId()),statisticsItem.getLeague().getSeason()))
						.games(game)
						.substitutes(substitute)
						.shots(shots)
						.goals(goals)
						.passes(passes)
						.tackles(tackles)
						.duels(duels)
						.dribbles(dribbles)
						.fouls(fouls)
						.cards(cards)
						.penalty(penalty).build();
				statisticList.add(statistic);
			}
			
			
			Birth birth=Birth.builder().date(exchange.getBody().getResponseList().get(0).getPlayer().getBirth().getDate())
					.place(exchange.getBody().getResponseList().get(0).getPlayer().getBirth().getPlace())
					.country(exchange.getBody().getResponseList().get(0).getPlayer().getBirth().getCountry()).build();
			birth=birthDal.add(birth);
			
			
			
			Player player= Player.builder().id(exchange.getBody().getResponseList().get(0).getPlayer().id)
					.name(exchange.getBody().getResponseList().get(0).getPlayer().getName())
					.firstName(exchange.getBody().getResponseList().get(0).getPlayer().firstname)
					.lastName(exchange.getBody().getResponseList().get(0).getPlayer().lastname)
					.birth(birth)
					.nationality(exchange.getBody().getResponseList().get(0).getPlayer().getNationality())
					.height(exchange.getBody().getResponseList().get(0).getPlayer().getHeight())
					.weight(exchange.getBody().getResponseList().get(0).getPlayer().getWeight())
					.injured(exchange.getBody().getResponseList().get(0).getPlayer().getInjured())
					.photo(exchange.getBody().getResponseList().get(0).getPlayer().getPhoto())
					.statistics(statisticList)
					.build();
					
			player=playerDal.add(player);
			System.out.println(player.getFirstName());
			
		//}
		
		
		
		
		return null;
	}
	
	@Data
	private static class PlayersData {

		private PlayerData player;

		private ArrayList<StatisticsOfPlayersScheduledDto> statistics;
		

		@Data
		private static class PlayerData {

			long id;
			String name;
			String firstname;
			String lastname;
			int age;
			BirthData birth;
			String nationality;
			String height;
			String weight;
			Boolean injured;
			String photo;

		}
		@Data
		private static class BirthData {

			Date date;
			String place;
			String country;

		}

	}
}
