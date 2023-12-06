package com.bc.revan.Entegration.Football;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
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
import com.bc.revan.DataAccess.IStandingMainNodeDal;
import com.bc.revan.DataAccess.IStatusOfFixtureDal;
import com.bc.revan.DataAccess.ITeamDal;
import com.bc.revan.DataAccess.ITeamsMainNodeOfFixtureDal;
import com.bc.revan.DataAccess.IVenueDal;
import com.bc.revan.DataAccess.IWeeklyMatchDal;
import com.bc.revan.Entegration.IWeeklyMatchesScheduledService;
import com.bc.revan.Entities.Fixture;
import com.bc.revan.Entities.FixtureMainNode;
import com.bc.revan.Entities.GoalsOfFixture;
import com.bc.revan.Entities.League;
import com.bc.revan.Entities.MainNodeOfScoreForFixture;
import com.bc.revan.Entities.PeriodsOfFixture;
import com.bc.revan.Entities.ScoreOfFixture;
import com.bc.revan.Entities.Season;
import com.bc.revan.Entities.Standing;
import com.bc.revan.Entities.StandingDetail;
import com.bc.revan.Entities.StandingMainNode;
import com.bc.revan.Entities.StatusOfFixture;
import com.bc.revan.Entities.Team;
import com.bc.revan.Entities.TeamsMainNodeOfFixture;
import com.bc.revan.Entities.Venue;
import com.bc.revan.Entities.WeeklyMatch;
import com.bc.revan.Entities.Base.BaseResponse;
import com.bc.revan.Entities.Enums.EnumForBetType;
import com.bc.revan.Entities.Enums.EnumForTimeOfScore;

import jakarta.transaction.Transactional;

@Service
@Component
public class WeeklyMatchesScheduledService extends BaseRequest implements IWeeklyMatchesScheduledService {

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
	IWeeklyMatchDal weeklyMatchDal;
	@Autowired
	IStandingMainNodeDal standingMainNodeDal;

	@Autowired
	public WeeklyMatchesScheduledService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Override
	@Transactional
	public List<WeeklyMatch> getWeeklyMatches() throws InterruptedException, ExecutionException {


		List<FixtureMainNode> notStartedList = fixtureMainNodeDal.getByStatus("NS");
		if (notStartedList==null) {
			return null;
		}
		
		
		 Long maxWeekCount = weeklyMatchDal.groupByWeekNumber().stream()
	                .max(Comparator.naturalOrder())
	                .orElse(null);
		 
		 
		for (FixtureMainNode fixtureMainNodeItem : notStartedList) {
			if (weeklyMatchDal.getByFixture(fixtureMainNodeItem.getFixture().getId()) != null) {
				continue;
			}
			Team homeTeam = fixtureMainNodeItem.getTeamsMainNode().getTeamHome();
			Team awayTeam = fixtureMainNodeItem.getTeamsMainNode().getTeamAway();

			StandingMainNode standingMainNode = standingMainNodeDal.getBySeasonAndLeagueId(
					fixtureMainNodeItem.getSeason().getYear(), fixtureMainNodeItem.getLeague().getId());

			/*
			 * int homeTeamRank; for (Standing standingItem :
			 * standingMainNode.getStanding()) { for (StandingDetail standingDetailItem :
			 * standingItem.getStandingDetail()) { if
			 * (standingDetailItem.getTeam().getId()==homeTeam.getId()) {
			 * homeTeamRank=standingDetailItem.getRank(); } } }
			 */
			int homeTeamRank = standingMainNode.getStanding().stream()
					.flatMap(standingItem -> standingItem.getStandingDetail().stream())
					.filter(standingDetailItem -> standingDetailItem.getTeam().getId() == homeTeam.getId())
					.map(StandingDetail::getRank).findFirst().orElse(0);

			int awayTeamRank = standingMainNode.getStanding().stream()
					.flatMap(standingItem -> standingItem.getStandingDetail().stream())
					.filter(standingDetailItem -> standingDetailItem.getTeam().getId() == awayTeam.getId())
					.map(StandingDetail::getRank).findFirst().orElse(0);

			List<FixtureMainNode> matchesOfHome = fixtureMainNodeDal.getBySeasonAndLeagueIdAndHomeTeamWithoutNS(2023,
					fixtureMainNodeItem.getLeague().getId(), homeTeam.getId());

			long goallessMatch = matchesOfHome.stream()
					.filter(matchOfHomeItem -> matchOfHomeItem.getGoals().getHome() == 0).count();
			if ((goallessMatch == 0 || goallessMatch == 1)
					&& (awayTeamRank != 1 && awayTeamRank != 2 && awayTeamRank != 3)) {

				WeeklyMatch weeklyMatch = WeeklyMatch.builder().fixtureMainNode(fixtureMainNodeItem)
						.betType(EnumForBetType.HOMEOVER05).createdDate(new Date(System.currentTimeMillis()))
						.weekNumber(maxWeekCount+1).build();
				weeklyMatchDal.add(weeklyMatch);
			}

			List<FixtureMainNode> matchesOfAway = fixtureMainNodeDal.getBySeasonAndLeagueIdAndAwayTeamWithoutNS(2023,
					fixtureMainNodeItem.getLeague().getId(), awayTeam.getId());

			goallessMatch = matchesOfAway.stream().filter(matchOfAwayItem -> matchOfAwayItem.getGoals().getAway() == 0)
					.count();
			if ((goallessMatch == 0 || goallessMatch == 1)
					&& (homeTeamRank != 1 && homeTeamRank != 2 && homeTeamRank != 3)) {

				WeeklyMatch weeklyMatch = WeeklyMatch.builder().fixtureMainNode(fixtureMainNodeItem)
						.betType(EnumForBetType.AWAYOVER05).createdDate(new Date(System.currentTimeMillis()))
						.weekNumber(maxWeekCount+1).build();
				weeklyMatchDal.add(weeklyMatch);

			}
		}

		return weeklyMatchDal.getAll();
	}

}
