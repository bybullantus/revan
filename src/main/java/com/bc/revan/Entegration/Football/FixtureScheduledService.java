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

import com.bc.revan.DataAccess.IFixtureDal;
import com.bc.revan.DataAccess.IFixtureMainNodeDal;
import com.bc.revan.DataAccess.IGoalsOfFixtureDal;
import com.bc.revan.DataAccess.ILeagueDal;
import com.bc.revan.DataAccess.IMainNodeOfScoreForFixtureDal;
import com.bc.revan.DataAccess.IPeriodsOfFixtureDal;
import com.bc.revan.DataAccess.IScoreOfFixtureDal;
import com.bc.revan.DataAccess.ISeasonDal;
import com.bc.revan.DataAccess.IStatusOfFixtureDal;
import com.bc.revan.DataAccess.ITeamDal;
import com.bc.revan.DataAccess.ITeamsMainNodeOfFixtureDal;
import com.bc.revan.DataAccess.IVenueDal;
import com.bc.revan.Entegration.IFixtureScheduledService;
import com.bc.revan.Entities.Fixture;
import com.bc.revan.Entities.FixtureMainNode;
import com.bc.revan.Entities.GoalsOfFixture;
import com.bc.revan.Entities.League;
import com.bc.revan.Entities.MainNodeOfScoreForFixture;
import com.bc.revan.Entities.PeriodsOfFixture;
import com.bc.revan.Entities.ScoreOfFixture;
import com.bc.revan.Entities.Season;
import com.bc.revan.Entities.StatusOfFixture;
import com.bc.revan.Entities.TeamsMainNodeOfFixture;
import com.bc.revan.Entities.Venue;
import com.bc.revan.Entities.Base.BaseResponse;
import com.bc.revan.Entities.Dto.FixtureScheduledDtos.FixtureForScheduledDto;
import com.bc.revan.Entities.Dto.FixtureScheduledDtos.ScoreOfFixtureForScheduledDto;
import com.bc.revan.Entities.Dto.FixtureScheduledDtos.TeamsOfFixtureForScheduledDto;
import com.bc.revan.Entities.Enums.EnumForTimeOfScore;

import jakarta.transaction.Transactional;
import lombok.Data;

@Service
@Component
public class FixtureScheduledService extends BaseRequest implements IFixtureScheduledService {

	// Tüm fikstur bilgileri içeri çekilmedi.

	@Autowired
	IScoreOfFixtureDal scoreOfFixtureDal;
	@Autowired
	IGoalsOfFixtureDal goalsOfFixtureDal;
	@Autowired
	ITeamDal teamDal;
	@Autowired
	ITeamsMainNodeOfFixtureDal teamsMainNodeOfFixtureDal;
	@Autowired
	ILeagueDal leagueDal;
	@Autowired
	ISeasonDal seasonDal;
	@Autowired
	IMainNodeOfScoreForFixtureDal mainNodeOfScoreForFixtureDal;
	@Autowired
	IFixtureMainNodeDal fixtureMainNodeDal;
	@Autowired
	IPeriodsOfFixtureDal periodsOfFixtureDal;
	@Autowired
	IStatusOfFixtureDal statusOfFixtureDal;
	@Autowired
	IFixtureDal fixtureDal;
	@Autowired
	IVenueDal venueDal;

	@Autowired
	public FixtureScheduledService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Override
	@Transactional
	public BaseResponse<FixtureData> getFixture() throws InterruptedException, ExecutionException {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", "application/json");
		headers.add("x-rapidapi-host", user);
		headers.add("x-rapidapi-key", password);

		ResponseEntity<BaseResponse<FixtureData>> exchange = null;
		List<Long> leagues = new ArrayList<Long>();
		/*leagues.add((long) 203);
		leagues.add((long) 88);
		leagues.add((long) 144);
		leagues.add((long) 135);*/
		leagues.add((long) 61);//
		//leagues.add((long) 39);
		leagues.add((long) 140);//
		//leagues.add((long) 78);

		for (Long leagueValue : leagues) {

			String seasonValue = "2023";

			HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

			String url = apiUrl + "fixtures?league=" + leagueValue + "&season=" + seasonValue + "&status=FT";

			exchange = restTemplate.exchange(url, HttpMethod.GET, entity,
					new ParameterizedTypeReference<BaseResponse<FixtureData>>() {
					});

			// System.out.println(exchange.getBody().getResponseList());

			for (FixtureData responseItem : exchange.getBody().getResponseList()) {

				// region Score

				Fixture fxtr = fixtureDal.getById(responseItem.getFixture().getId());
				if (fxtr != null && fxtr.getStatus().getShortOfStatus() == "FT") {

					continue;
				} else if (fxtr != null && fxtr.getStatus().getShortOfStatus() != "FT") {
					FixtureMainNode checkFixtureMain = fixtureMainNodeDal
							.getByFixture(responseItem.getFixture().getId());
					List<ScoreOfFixture> scores = new ArrayList<ScoreOfFixture>();
					for (ScoreOfFixture scoresItem : checkFixtureMain.getScore().getScores()) {
						if (scoresItem.getTimeOfScore().equals(EnumForTimeOfScore.halftime)) {
							ScoreOfFixture halfTime = scoresItem;
							halfTime = ScoreOfFixture.builder().id(scoresItem.getId())
									.home(responseItem.getScore().getHalftime().getHome())
									.away(responseItem.getScore().getHalftime().getAway())
									.timeOfScore(EnumForTimeOfScore.halftime).build();
							scoreOfFixtureDal.update(scoresItem);
							scores.add(halfTime);
						} else if (scoresItem.getTimeOfScore().equals(EnumForTimeOfScore.fulltime)) {
							ScoreOfFixture fullTime = scoresItem;
							fullTime = ScoreOfFixture.builder().id(scoresItem.getId())
									.home(responseItem.getScore().getFulltime().getHome())
									.away(responseItem.getScore().getFulltime().getAway())
									.timeOfScore(EnumForTimeOfScore.fulltime).build();
							scoreOfFixtureDal.update(scoresItem);
							scores.add(fullTime);
						} else if (scoresItem.getTimeOfScore().equals(EnumForTimeOfScore.extratime)) {
							ScoreOfFixture extraTime = scoresItem;
							extraTime = ScoreOfFixture.builder().id(scoresItem.getId())
									.home(responseItem.getScore().getExtratime().getHome())
									.away(responseItem.getScore().getExtratime().getAway())
									.timeOfScore(EnumForTimeOfScore.extratime).build();

							scoreOfFixtureDal.update(scoresItem);
							scores.add(extraTime);
						} else if (scoresItem.getTimeOfScore().equals(EnumForTimeOfScore.penalty)) {
							ScoreOfFixture penalty = scoresItem;
							penalty = ScoreOfFixture.builder().id(scoresItem.getId())
									.home(responseItem.getScore().getPenalty().getHome())
									.away(responseItem.getScore().getPenalty().getAway())
									.timeOfScore(EnumForTimeOfScore.extratime).build();
							scoreOfFixtureDal.update(scoresItem);
							scores.add(penalty);
						}

					}

					MainNodeOfScoreForFixture mainScore = checkFixtureMain.getScore();
					mainScore = MainNodeOfScoreForFixture.builder().id(checkFixtureMain.getScore().getId())
							.scores(scores).build();
					mainScore = mainNodeOfScoreForFixtureDal.update(mainScore);
					// endregion Score

					GoalsOfFixture goals = checkFixtureMain.getGoals();
					goals = GoalsOfFixture.builder().id(checkFixtureMain.getGoals().getId())
							.home(responseItem.getGoals().getHome()).away(responseItem.getGoals().getAway()).build();
					goals = goalsOfFixtureDal.update(goals);

					// region teams
					TeamsMainNodeOfFixture teamsMainNode = checkFixtureMain.getTeamsMainNode();
					teamsMainNode = TeamsMainNodeOfFixture.builder().id(checkFixtureMain.getTeamsMainNode().getId())
							.teamHome(teamDal.getById(responseItem.getTeams().getHome().getId()))
							.teamAway(teamDal.getById(responseItem.getTeams().getAway().getId())).build();
					teamsMainNode = teamsMainNodeOfFixtureDal.update(teamsMainNode);
					// endregion teams

					League league = leagueDal.getById(responseItem.getLeague().getId());

					Season season = seasonDal.getByLeagueAndYear(league, Integer.parseInt(seasonValue));

					// region periods
					PeriodsOfFixture periods = checkFixtureMain.getFixture().getPeriods();
					periods = PeriodsOfFixture.builder().id(checkFixtureMain.getFixture().getPeriods().getId())
							.first(responseItem.getFixture().getPeriods().getFirst())
							.second(responseItem.getFixture().getPeriods().getSecond()).build();
					periods = periodsOfFixtureDal.update(periods);

					// endregion periods

					// region status
					StatusOfFixture status = checkFixtureMain.getFixture().getStatus();
					status = StatusOfFixture.builder().id(checkFixtureMain.getFixture().getStatus().getId())
							.longOfStatus(responseItem.fixture.getStatus().getLongOfStatus())
							.shortOfStatus(responseItem.fixture.getStatus().getShortOfStatus())
							.elapsed(responseItem.fixture.getStatus().getElapsed()).build();
					status = statusOfFixtureDal.update(status);

					// endregion status

					System.out.println(responseItem.getFixture().getVenue().getId());
					// region venue
					Venue venue = venueDal.getById(responseItem.getFixture().getVenue().getId());
					if (venue == null) {
						venue = Venue.builder().id(responseItem.getFixture().getVenue().getId())
								.name(responseItem.getFixture().getVenue().getName())
								.city(responseItem.getFixture().getVenue().getCity()).build();
						venue = venueDal.add(venue);
					}
					// endregion venue

					Fixture fixture = checkFixtureMain.getFixture();
					fixture = Fixture.builder().id(responseItem.getFixture().getId())
							.referee(responseItem.getFixture().getReferee())
							.timezone(responseItem.getFixture().getTimezone()).date(responseItem.getFixture().getDate())
							.timestamp(responseItem.getFixture().getTimestamp()).periods(periods).status(status)
							.venue(venue).build();
					fixture = fixtureDal.update(fixture);

					FixtureMainNode fixtureMainNode = checkFixtureMain;
					fixtureMainNode = FixtureMainNode.builder().id(checkFixtureMain.getId())
							.teamsMainNode(teamsMainNode).score(mainScore).league(league).goals(goals)
							.teamsMainNode(teamsMainNode).fixture(fixture).season(season)
							.round(responseItem.getLeague().getRound()).build();
					fixtureMainNode = fixtureMainNodeDal.update(fixtureMainNode);
				} else {
					ScoreOfFixture halfTime = ScoreOfFixture.builder()
							.home(responseItem.getScore().getHalftime().getHome())
							.away(responseItem.getScore().getHalftime().getAway())
							.timeOfScore(EnumForTimeOfScore.halftime).build();
					halfTime = scoreOfFixtureDal.add(halfTime);
					ScoreOfFixture fullTime = ScoreOfFixture.builder()
							.home(responseItem.getScore().getFulltime().getHome())
							.away(responseItem.getScore().getFulltime().getAway())
							.timeOfScore(EnumForTimeOfScore.fulltime).build();
					fullTime = scoreOfFixtureDal.add(fullTime);
					ScoreOfFixture extraTime = ScoreOfFixture.builder()
							.home(responseItem.getScore().getExtratime().getHome())
							.away(responseItem.getScore().getExtratime().getAway())
							.timeOfScore(EnumForTimeOfScore.extratime).build();
					extraTime = scoreOfFixtureDal.add(extraTime);
					ScoreOfFixture penalty = ScoreOfFixture.builder()
							.home(responseItem.getScore().getPenalty().getHome())
							.away(responseItem.getScore().getPenalty().getAway())
							.timeOfScore(EnumForTimeOfScore.penalty).build();
					penalty = scoreOfFixtureDal.add(penalty);

					List<ScoreOfFixture> scores = new ArrayList<ScoreOfFixture>();
					scores.add(halfTime);
					scores.add(fullTime);
					scores.add(extraTime);
					scores.add(penalty);

					MainNodeOfScoreForFixture mainScore = MainNodeOfScoreForFixture.builder().scores(scores).build();
					mainScore = mainNodeOfScoreForFixtureDal.add(mainScore);
					// endregion Score

					GoalsOfFixture goals = GoalsOfFixture.builder().home(responseItem.getGoals().getHome())
							.away(responseItem.getGoals().getAway()).build();
					goals = goalsOfFixtureDal.add(goals);

					// region teams

					TeamsMainNodeOfFixture teamsMainNode = TeamsMainNodeOfFixture.builder()
							.teamHome(teamDal.getById(responseItem.getTeams().getHome().getId()))
							.teamAway(teamDal.getById(responseItem.getTeams().getAway().getId())).build();
					teamsMainNode = teamsMainNodeOfFixtureDal.add(teamsMainNode);
					// endregion teams

					League league = leagueDal.getById(responseItem.getLeague().getId());

					Season season = seasonDal.getByLeagueAndYear(league, Integer.parseInt(seasonValue));

					// region periods
					PeriodsOfFixture periods = PeriodsOfFixture.builder()
							.first(responseItem.getFixture().getPeriods().getFirst())
							.second(responseItem.getFixture().getPeriods().getSecond()).build();
					periods = periodsOfFixtureDal.add(periods);

					// endregion periods

					// region status

					StatusOfFixture status = StatusOfFixture.builder()
							.longOfStatus(responseItem.fixture.getStatus().getLongOfStatus())
							.shortOfStatus(responseItem.fixture.getStatus().getShortOfStatus())
							.elapsed(responseItem.fixture.getStatus().getElapsed()).build();
					status = statusOfFixtureDal.add(status);

					// endregion status

					System.out.println(responseItem.getFixture().getVenue().getId());
					// region venue
					Venue venue = venueDal.getById(responseItem.getFixture().getVenue().getId());
					if (venue == null) {
						venue = Venue.builder().id(responseItem.getFixture().getVenue().getId())
								.name(responseItem.getFixture().getVenue().getName())
								.city(responseItem.getFixture().getVenue().getCity()).build();
						venue = venueDal.add(venue);
					}
					// endregion venue

					Fixture fixture = Fixture.builder().id(responseItem.getFixture().getId())
							.referee(responseItem.getFixture().getReferee())
							.timezone(responseItem.getFixture().getTimezone()).date(responseItem.getFixture().getDate())
							.timestamp(responseItem.getFixture().getTimestamp()).periods(periods).status(status)
							.venue(venue).build();
					fixture = fixtureDal.add(fixture);

					FixtureMainNode fixtureMainNode = FixtureMainNode.builder().teamsMainNode(teamsMainNode)
							.score(mainScore).league(league).goals(goals).teamsMainNode(teamsMainNode).fixture(fixture)
							.season(season).round(responseItem.getLeague().getRound()).build();
					fixtureMainNode = fixtureMainNodeDal.add(fixtureMainNode);
				}

			}

			url = apiUrl + "fixtures/rounds?league=" + leagueValue + "&season=" + seasonValue + "&current=true";
			ResponseEntity<BaseResponse<String>> exchange2 = null;
			exchange2 = restTemplate.exchange(url, HttpMethod.GET, entity,
					new ParameterizedTypeReference<BaseResponse<String>>() {
					});

			
			/************/
		
			/*boolean hasStatusNS = fixtureMainNodeDal.getByRound(exchange2.getBody().getResponseList().get(0)).stream()
					.anyMatch(
							checkRoundItem -> checkRoundItem.getFixture().getStatus().getShortOfStatus().equals("NS"));

			if (hasStatusNS)
				continue;*/

			/************/
			
			for (String responseItem2 : exchange2.getBody().getResponseList()) {

				url = apiUrl + "fixtures?league=" + leagueValue + "&season=" + seasonValue + "&round=" + responseItem2;
				
				
				if (leagueValue==61) {
					url = apiUrl + "fixtures?league=" + leagueValue + "&season=" + seasonValue + "&round=Regular Season - 14";
				}
				else if (leagueValue==140) {
					url = apiUrl + "fixtures?league=" + leagueValue + "&season=" + seasonValue + "&round=Regular Season - 15";

				}
				
				exchange = restTemplate.exchange(url, HttpMethod.GET, entity,
						new ParameterizedTypeReference<BaseResponse<FixtureData>>() {
						});

				for (FixtureData responseItem : exchange.getBody().getResponseList()) {

					// region Score
					FixtureMainNode checkFixtureMain = fixtureMainNodeDal
							.getByFixture(responseItem.getFixture().getId());

					if (checkFixtureMain != null) {
						continue;
					} else {
						ScoreOfFixture halfTime = ScoreOfFixture.builder()
								.home(responseItem.getScore().getHalftime().getHome())
								.away(responseItem.getScore().getHalftime().getAway())
								.timeOfScore(EnumForTimeOfScore.halftime).build();
						halfTime = scoreOfFixtureDal.add(halfTime);
						ScoreOfFixture fullTime = ScoreOfFixture.builder()
								.home(responseItem.getScore().getFulltime().getHome())
								.away(responseItem.getScore().getFulltime().getAway())
								.timeOfScore(EnumForTimeOfScore.fulltime).build();
						fullTime = scoreOfFixtureDal.add(fullTime);
						ScoreOfFixture extraTime = ScoreOfFixture.builder()
								.home(responseItem.getScore().getExtratime().getHome())
								.away(responseItem.getScore().getExtratime().getAway())
								.timeOfScore(EnumForTimeOfScore.extratime).build();
						extraTime = scoreOfFixtureDal.add(extraTime);
						ScoreOfFixture penalty = ScoreOfFixture.builder()
								.home(responseItem.getScore().getPenalty().getHome())
								.away(responseItem.getScore().getPenalty().getAway())
								.timeOfScore(EnumForTimeOfScore.penalty).build();
						penalty = scoreOfFixtureDal.add(penalty);

						List<ScoreOfFixture> scores = new ArrayList<ScoreOfFixture>();
						scores.add(halfTime);
						scores.add(fullTime);
						scores.add(extraTime);
						scores.add(penalty);

						MainNodeOfScoreForFixture mainScore = MainNodeOfScoreForFixture.builder().scores(scores)
								.build();
						mainScore = mainNodeOfScoreForFixtureDal.add(mainScore);
						// endregion Score

						GoalsOfFixture goals = GoalsOfFixture.builder().home(responseItem.getGoals().getHome())
								.away(responseItem.getGoals().getAway()).build();
						goals = goalsOfFixtureDal.add(goals);

						// region teams

						TeamsMainNodeOfFixture teamsMainNode = TeamsMainNodeOfFixture.builder()
								.teamHome(teamDal.getById(responseItem.getTeams().getHome().getId()))
								.teamAway(teamDal.getById(responseItem.getTeams().getAway().getId())).build();
						teamsMainNode = teamsMainNodeOfFixtureDal.add(teamsMainNode);
						// endregion teams

						League league = leagueDal.getById(responseItem.getLeague().getId());

						Season season = seasonDal.getByLeagueAndYear(league, Integer.parseInt(seasonValue));

						// region periods
						PeriodsOfFixture periods = PeriodsOfFixture.builder()
								.first(responseItem.getFixture().getPeriods().getFirst())
								.second(responseItem.getFixture().getPeriods().getSecond()).build();
						periods = periodsOfFixtureDal.add(periods);

						// endregion periods

						// region status

						StatusOfFixture status = StatusOfFixture.builder()
								.longOfStatus(responseItem.fixture.getStatus().getLongOfStatus())
								.shortOfStatus(responseItem.fixture.getStatus().getShortOfStatus())
								.elapsed(responseItem.fixture.getStatus().getElapsed()).build();
						status = statusOfFixtureDal.add(status);

						// endregion status

						System.out.println(responseItem.getFixture().getVenue().getId());
						// region venue
						Venue venue = venueDal.getById(responseItem.getFixture().getVenue().getId());
						if (venue == null) {
							venue = Venue.builder().id(responseItem.getFixture().getVenue().getId())
									.name(responseItem.getFixture().getVenue().getName())
									.city(responseItem.getFixture().getVenue().getCity()).build();
							venue = venueDal.add(venue);
						}
						// endregion venue

						Fixture fixture = Fixture.builder().id(responseItem.getFixture().getId())
								.referee(responseItem.getFixture().getReferee())
								.timezone(responseItem.getFixture().getTimezone())
								.date(responseItem.getFixture().getDate())
								.timestamp(responseItem.getFixture().getTimestamp()).periods(periods).status(status)
								.venue(venue).build();
						fixture = fixtureDal.add(fixture);

						FixtureMainNode fixtureMainNode = FixtureMainNode.builder().teamsMainNode(teamsMainNode)
								.score(mainScore).league(league).goals(goals).teamsMainNode(teamsMainNode)
								.fixture(fixture).season(season).round(responseItem.getLeague().getRound()).build();
						fixtureMainNode = fixtureMainNodeDal.add(fixtureMainNode);
					}

				}

			}

		}
		return exchange.getBody();
	}

	@Data
	private static class FixtureData {

		FixtureForScheduledDto fixture;

		LeagueOfFixtureData league;

		TeamsOfFixtureForScheduledDto teams;

		GoalsOfFixtureData goals;

		ScoreOfFixtureForScheduledDto score;

		@Data
		public static class LeagueOfFixtureData {

			long id;
			String name;
			String country;
			String logo;
			String flag;
			int season;
			String round;
		}

		@Data
		public static class GoalsOfFixtureData {

			int home;
			int away;
		}

	}

}
