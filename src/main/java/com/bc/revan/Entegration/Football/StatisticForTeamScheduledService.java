package com.bc.revan.Entegration.Football;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.bc.revan.DataAccess.IBiggestOfStatisticsDal;
import com.bc.revan.DataAccess.ICardsOfStatisticsDal;
import com.bc.revan.DataAccess.ICleanSheetOfStatisticsDal;
import com.bc.revan.DataAccess.IFailedToScoreOfStatisticsDal;
import com.bc.revan.DataAccess.IFixtureOfStatisticsDal;
import com.bc.revan.DataAccess.IForAndAgainstOfBiggestDal;
import com.bc.revan.DataAccess.IForAndAgainstOfGoalsDal;
import com.bc.revan.DataAccess.IGoalsOfBiggestDal;
import com.bc.revan.DataAccess.IGoalsOfStatisticsDal;
import com.bc.revan.DataAccess.ILeagueDal;
import com.bc.revan.DataAccess.ILineupOfStatisticsDal;
import com.bc.revan.DataAccess.IMinuteDal;
import com.bc.revan.DataAccess.IPWDLDal;
import com.bc.revan.DataAccess.IPenaltyOfStatisticsDal;
import com.bc.revan.DataAccess.IScoredAndMissedOfPenaltyDal;
import com.bc.revan.DataAccess.ISeasonDal;
import com.bc.revan.DataAccess.IStatisticForTeamDal;
import com.bc.revan.DataAccess.IStreakOfBiggestDal;
import com.bc.revan.DataAccess.ITeamDal;
import com.bc.revan.DataAccess.ITimeIntervalDal;
import com.bc.revan.DataAccess.ITotalAndAverageDal;
import com.bc.revan.DataAccess.IWinsAndLosesOfBiggestDal;
import com.bc.revan.DataAccess.IYellowAndRedOfCardsDal;
import com.bc.revan.Entegration.IStatisticForTeamScheduledService;
import com.bc.revan.Entities.BiggestOfStatistics;
import com.bc.revan.Entities.CardsOfStatistics;
import com.bc.revan.Entities.CleanSheetOfStatistics;
import com.bc.revan.Entities.FailedToScoreOfStatistics;
import com.bc.revan.Entities.FixtureOfStatistics;
import com.bc.revan.Entities.ForAndAgainstOfBiggest;
import com.bc.revan.Entities.ForAndAgainstOfGoals;
import com.bc.revan.Entities.GoalsOfBiggest;
import com.bc.revan.Entities.GoalsOfStatistics;
import com.bc.revan.Entities.League;
import com.bc.revan.Entities.LineupOfStatistics;
import com.bc.revan.Entities.Minute;
import com.bc.revan.Entities.PWDLFixture;
import com.bc.revan.Entities.PenaltyOfStatistics;
import com.bc.revan.Entities.ScoredAndMissedOfPenalty;
import com.bc.revan.Entities.StatisticForTeam;
import com.bc.revan.Entities.StreakOfBiggest;
import com.bc.revan.Entities.Team;
import com.bc.revan.Entities.TimeInterval;
import com.bc.revan.Entities.TotalAndAverage;
import com.bc.revan.Entities.WinsAndLosesOfBiggest;
import com.bc.revan.Entities.YellowAndRedOfCards;
import com.bc.revan.Entities.Base.BaseResponseForObject;
import com.bc.revan.Entities.Dto.StatisticsForTeamScheduledDtos.BiggestForStatisticsScheduledDto;
import com.bc.revan.Entities.Dto.StatisticsForTeamScheduledDtos.CardsForStatisticsScheduledDto;
import com.bc.revan.Entities.Dto.StatisticsForTeamScheduledDtos.CleanSheetForStatisticsScheduledDto;
import com.bc.revan.Entities.Dto.StatisticsForTeamScheduledDtos.FailedToScoreOfStatisticsScheduledDto;
import com.bc.revan.Entities.Dto.StatisticsForTeamScheduledDtos.FixtureForStatisticsScheduledDto;
import com.bc.revan.Entities.Dto.StatisticsForTeamScheduledDtos.GoalsForStatisticsScheduledDto;
import com.bc.revan.Entities.Dto.StatisticsForTeamScheduledDtos.LeagueForStatisticsScheduledDto;
import com.bc.revan.Entities.Dto.StatisticsForTeamScheduledDtos.LineupOfStatisticsScheduledDto;
import com.bc.revan.Entities.Dto.StatisticsForTeamScheduledDtos.PenaltyForStatisticsScheduledDto;
import com.bc.revan.Entities.Dto.StatisticsForTeamScheduledDtos.TeamForStatisticsScheduledDto;
import com.bc.revan.Entities.Enums.EnumForForAndAgainst;
import com.bc.revan.Entities.Enums.EnumForPWDL;
import com.bc.revan.Entities.Enums.EnumForScoredAndMissed;
import com.bc.revan.Entities.Enums.EnumForTimeInterval;
import com.bc.revan.Entities.Enums.EnumForTotalAndAverage;
import com.bc.revan.Entities.Enums.EnumForWinsAndLoses;
import com.bc.revan.Entities.Enums.EnumForYellowAndRed;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Service
@Component
public class StatisticForTeamScheduledService extends BaseRequest implements IStatisticForTeamScheduledService {

	@Autowired // eklersek constructor a eklememize gerek yok
	private IFixtureOfStatisticsDal fixtureOfStatisticsDal;
	@Autowired
	private IPWDLDal pwdlDal;
	@Autowired
	private IStatisticForTeamDal statisticForTeamDal;
	@Autowired
	private ILeagueDal leagueDal;
	@Autowired
	private ITeamDal teamDal;
	@Autowired
	private ISeasonDal seasonDal;
	@Autowired
	private ITimeIntervalDal timeIntervalDal;
	@Autowired
	private IMinuteDal minuteDal;
	@Autowired
	private ITotalAndAverageDal totalAndAverageDal;
	@Autowired
	private IForAndAgainstOfGoalsDal forAndAgainstOfGoalsDal;
	@Autowired
	private IGoalsOfStatisticsDal goalsOfStatisticsDal;
	@Autowired
	private IBiggestOfStatisticsDal biggestOfStatisticsDal;
	@Autowired
	private IStreakOfBiggestDal streakOfBiggestDal;
	@Autowired
	private IWinsAndLosesOfBiggestDal winsAndLosesOfBiggestDal;
	@Autowired
	private IGoalsOfBiggestDal goalsOfBiggestDal;
	@Autowired
	private IForAndAgainstOfBiggestDal forAndAgainstOfBiggestDal;
	@Autowired
	private ICleanSheetOfStatisticsDal cleanSheetOfStatisticsDal;
	@Autowired
	private IFailedToScoreOfStatisticsDal failedToScoreOfStatisticsDal;
	@Autowired
	private IPenaltyOfStatisticsDal penaltyOfStatisticsDal;
	@Autowired
	private IScoredAndMissedOfPenaltyDal scoredAndMissedOfPenaltyDal;
	@Autowired
	private ILineupOfStatisticsDal lineupOfStatisticsDal;
	@Autowired
	private IYellowAndRedOfCardsDal yellowAndRedOfCardsDal;
	@Autowired
	private ICardsOfStatisticsDal cardsOfStatisticsDal;

	@Autowired
	public StatisticForTeamScheduledService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Transactional
	@Override
	public BaseResponseForObject<StatisticsData> getStatisticsForTeam() {

		// PWDLFixture sttData=PWDLFixture.builder().total(1).build();

		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", "application/json");
		headers.add("x-rapidapi-host", user);
		headers.add("x-rapidapi-key", password);

		ResponseEntity<BaseResponseForObject<StatisticsData>> exchange = null;
		List<String> countrys = new ArrayList<String>();
		//countrys.add("Turkey");
		//countrys.add("Netherlands");
		//countrys.add("Belgium");
		//countrys.add("Italy");
		//countrys.add("France");
		//countrys.add("England");
		//countrys.add("Spain");
		//countrys.add("Germany");
		for (String countryValue : countrys) {
			for (Team teamResponse : teamDal.getByCountry(countryValue)) {

				int seasonValue = 0;
				long leagueValue = 0;
				long teamValue = 0;
				String country = teamResponse.getCountry();
				if ("Turkey".equals(country)) {
					seasonValue = 2023;
					leagueValue = 203;
					teamValue = teamResponse.getId();
				} else if ("Netherlands".equals(country)) {
					seasonValue = 2023;
					leagueValue = 88;
					teamValue = teamResponse.getId();
				} else if ("Belgium".equals(country)) {
					seasonValue = 2023;
					leagueValue = 144;
					teamValue = teamResponse.getId();
				} else if ("Italy".equals(country)) {
					seasonValue = 2023;
					leagueValue = 135;
					teamValue = teamResponse.getId();
				} else if ("France".equals(country)) {
					seasonValue = 2023;
					leagueValue = 61;
					teamValue = teamResponse.getId();
				} else if ("England".equals(country)) {
					seasonValue = 2023;
					leagueValue = 39;
					teamValue = teamResponse.getId();
				} else if ("Spain".equals(country)) {
					seasonValue = 2023;
					leagueValue = 140;
					teamValue = teamResponse.getId();
				} else if ("Germany".equals(country)) {
					seasonValue = 2023;
					leagueValue = 78;
					teamValue = teamResponse.getId();
				}

				StatisticForTeam dummy = statisticForTeamDal.getBySeasonAndLeagueIdAndTeamId(seasonValue, leagueValue,
						teamValue);
				if (dummy != null) {
					System.out.println("burda");
					continue;
				}

				String url = apiUrl + "teams/statistics?season=" + seasonValue + "&team=" + teamValue + "&league="
						+ leagueValue;

				UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url);

				HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
				exchange = restTemplate.exchange(url, HttpMethod.GET, entity,
						new ParameterizedTypeReference<BaseResponseForObject<StatisticsData>>() {
						});
				StatisticsData responseItem = exchange.getBody().getResponsObject();

				// System.out.println(teamDal.getById(responseItem.getTeam().getId()));

				PWDLFixture pwdlFixturePlayed = PWDLFixture.builder()
						.home(responseItem.getFixtures().getPlayed().getHome())
						.away(responseItem.getFixtures().getPlayed().getAway())
						.total(responseItem.getFixtures().getPlayed().getTotal()).pwdl(EnumForPWDL.played).build();
				pwdlFixturePlayed = pwdlDal.add(pwdlFixturePlayed);

				/*
				 * Builder'dan önce
				 * pwdlFixturePlayed.setHome(responseItem.getFixtures().getPlayed().getHome());
				 * pwdlFixturePlayed.setAway(responseItem.getFixtures().getPlayed().getAway());
				 * pwdlFixturePlayed.setTotal(responseItem.getFixtures().getPlayed().getTotal())
				 * ; pwdlFixturePlayed.setPwdl(EnumForPWDL.played);
				 * pwdlFixturePlayed=pwdlDal.add(pwdlFixturePlayed);
				 */

				PWDLFixture pwdlFixtureWins = PWDLFixture.builder().home(responseItem.getFixtures().getWins().getHome())
						.away(responseItem.getFixtures().getWins().getAway())
						.total(responseItem.getFixtures().getWins().getTotal()).pwdl(EnumForPWDL.wins).build();
				pwdlFixtureWins = pwdlDal.add(pwdlFixtureWins);

				PWDLFixture pwdlFixtureDraws = PWDLFixture.builder()
						.home(responseItem.getFixtures().getDraws().getHome())
						.away(responseItem.getFixtures().getDraws().getAway())
						.total(responseItem.getFixtures().getDraws().getTotal()).pwdl(EnumForPWDL.draws).build();
				pwdlFixtureDraws = pwdlDal.add(pwdlFixtureDraws);

				PWDLFixture pwdlFixtureLoses = PWDLFixture.builder()
						.home(responseItem.getFixtures().getLoses().getHome())
						.away(responseItem.getFixtures().getLoses().getAway())
						.total(responseItem.getFixtures().getLoses().getTotal()).pwdl(EnumForPWDL.loses).build();
				pwdlFixtureLoses = pwdlDal.add(pwdlFixtureLoses);

				List<PWDLFixture> pwdlList = new ArrayList<PWDLFixture>();
				pwdlList.add(pwdlFixturePlayed);
				pwdlList.add(pwdlFixtureWins);
				pwdlList.add(pwdlFixtureLoses);
				pwdlList.add(pwdlFixtureDraws);

				FixtureOfStatistics fixtureOfStatistics = FixtureOfStatistics.builder().pwdlFixture(pwdlList).build();
				fixtureOfStatistics = fixtureOfStatisticsDal.add(fixtureOfStatistics);

				// Region Goals
				// Region For
				TimeInterval zeroToFifteenOfFor = TimeInterval.builder()
						.total(responseItem.getGoals().getForOfGoals().getMinute().getZeroToFifteen().getTotal())
						.percentage(
								responseItem.getGoals().getForOfGoals().getMinute().getZeroToFifteen().getPercentage())
						.timeInterval(EnumForTimeInterval.ZERO_TO_FIFTEEN).build();
				zeroToFifteenOfFor = timeIntervalDal.add(zeroToFifteenOfFor);

				TimeInterval sixteenToThirtyOfFor = TimeInterval.builder()
						.total(responseItem.getGoals().getForOfGoals().getMinute().getSixteenToThirty().getTotal())
						.percentage(responseItem.getGoals().getForOfGoals().getMinute().getSixteenToThirty()
								.getPercentage())
						.timeInterval(EnumForTimeInterval.SIXTEEN_TO_THIRTY).build();
				sixteenToThirtyOfFor = timeIntervalDal.add(sixteenToThirtyOfFor);

				TimeInterval thirtyOneToFortyFiveOfFor = TimeInterval.builder()
						.total(responseItem.getGoals().getForOfGoals().getMinute().getThirtyOneToFortyFive().getTotal())
						.percentage(responseItem.getGoals().getForOfGoals().getMinute().getThirtyOneToFortyFive()
								.getPercentage())
						.timeInterval(EnumForTimeInterval.THIRTY_ONE_TO_FORTY_FIVE).build();
				thirtyOneToFortyFiveOfFor = timeIntervalDal.add(thirtyOneToFortyFiveOfFor);

				TimeInterval fortySixToSixtyOfFor = TimeInterval.builder()
						.total(responseItem.getGoals().getForOfGoals().getMinute().getFortySixToSixty().getTotal())
						.percentage(responseItem.getGoals().getForOfGoals().getMinute().getFortySixToSixty()
								.getPercentage())
						.timeInterval(EnumForTimeInterval.FORTY_SIX_TO_SIXTY).build();
				fortySixToSixtyOfFor = timeIntervalDal.add(fortySixToSixtyOfFor);

				TimeInterval sixtyOneToSeventyFiveOfFor = TimeInterval.builder()
						.total(responseItem.getGoals().getForOfGoals().getMinute().getSixtyOneToSeventyFive()
								.getTotal())
						.percentage(responseItem.getGoals().getForOfGoals().getMinute().getSixtyOneToSeventyFive()
								.getPercentage())
						.timeInterval(EnumForTimeInterval.SIXTY_ONE_TO_SEVENTY_FIVE).build();
				sixtyOneToSeventyFiveOfFor = timeIntervalDal.add(sixtyOneToSeventyFiveOfFor);

				TimeInterval seventySixToNinetyOfFor = TimeInterval.builder()
						.total(responseItem.getGoals().getForOfGoals().getMinute().getSeventySixToNinety().getTotal())
						.percentage(responseItem.getGoals().getForOfGoals().getMinute().getSeventySixToNinety()
								.getPercentage())
						.timeInterval(EnumForTimeInterval.SEVENTY_SIX_TO_NINETY).build();
				seventySixToNinetyOfFor = timeIntervalDal.add(seventySixToNinetyOfFor);

				TimeInterval ninetyOneToOneHundredFiveOfFor = TimeInterval.builder()
						.total(responseItem.getGoals().getForOfGoals().getMinute().getNinetyOneToOneHundredFive()
								.getTotal())
						.percentage(responseItem.getGoals().getForOfGoals().getMinute().getNinetyOneToOneHundredFive()
								.getPercentage())
						.timeInterval(EnumForTimeInterval.NINETY_ONE_TO_ONE_HUNDRED_FIVE).build();
				ninetyOneToOneHundredFiveOfFor = timeIntervalDal.add(ninetyOneToOneHundredFiveOfFor);

				TimeInterval oneHundredSixToOneTwentyOfFor = TimeInterval.builder()
						.total(responseItem.getGoals().getForOfGoals().getMinute().getOneHundredSixToOneHundredTwenty()
								.getTotal())
						.percentage(responseItem.getGoals().getForOfGoals().getMinute()
								.getOneHundredSixToOneHundredTwenty().getPercentage())
						.timeInterval(EnumForTimeInterval.ONE_HUNDRED_SIX_TO_ONE_TWENTY).build();
				oneHundredSixToOneTwentyOfFor = timeIntervalDal.add(oneHundredSixToOneTwentyOfFor);

				List<TimeInterval> timeIntervalListOfFor = new ArrayList<TimeInterval>();
				timeIntervalListOfFor.add(zeroToFifteenOfFor);
				timeIntervalListOfFor.add(sixteenToThirtyOfFor);
				timeIntervalListOfFor.add(thirtyOneToFortyFiveOfFor);
				timeIntervalListOfFor.add(fortySixToSixtyOfFor);
				timeIntervalListOfFor.add(sixtyOneToSeventyFiveOfFor);
				timeIntervalListOfFor.add(seventySixToNinetyOfFor);
				timeIntervalListOfFor.add(ninetyOneToOneHundredFiveOfFor);
				timeIntervalListOfFor.add(oneHundredSixToOneTwentyOfFor);

				Minute minuteOfFor = Minute.builder().timeIntervals(timeIntervalListOfFor).build();
				minuteOfFor = minuteDal.add(minuteOfFor);

				TotalAndAverage totalofFor = TotalAndAverage.builder()
						.home(responseItem.getGoals().getForOfGoals().getTotal().getHome())
						.away(responseItem.getGoals().getForOfGoals().getTotal().getAway())
						.total(responseItem.getGoals().getForOfGoals().getTotal().getTotal())
						.totalAndAverage(EnumForTotalAndAverage.total).build();
				totalofFor = totalAndAverageDal.add(totalofFor);

				TotalAndAverage averageOfFor = TotalAndAverage.builder()
						.home(new BigDecimal(responseItem.getGoals().getForOfGoals().getAverage().getHome()))
						.away(new BigDecimal(responseItem.getGoals().getForOfGoals().getAverage().getAway()))
						.total(new BigDecimal(responseItem.getGoals().getForOfGoals().getAverage().getTotal()))
						.totalAndAverage(EnumForTotalAndAverage.average).build();
				averageOfFor = totalAndAverageDal.add(averageOfFor);

				List<TotalAndAverage> totalAndAverageOfFor = new ArrayList<TotalAndAverage>();
				totalAndAverageOfFor.add(totalofFor);
				totalAndAverageOfFor.add(averageOfFor);

				ForAndAgainstOfGoals forAndAgainstOfGoalsOfFor = ForAndAgainstOfGoals.builder().minute(minuteOfFor)
						.totalAndAverage(totalAndAverageOfFor).ForAndAgainst(EnumForForAndAgainst.For).build();
				forAndAgainstOfGoalsOfFor = forAndAgainstOfGoalsDal.add(forAndAgainstOfGoalsOfFor);
				// EndRegion For

				// Region Against
				TimeInterval zeroToFifteenOfAgainst = TimeInterval.builder()
						.total(responseItem.getGoals().getAgainst().getMinute().getZeroToFifteen().getTotal())
						.percentage(responseItem.getGoals().getAgainst().getMinute().getZeroToFifteen().getPercentage())
						.timeInterval(EnumForTimeInterval.ZERO_TO_FIFTEEN).build();
				zeroToFifteenOfAgainst = timeIntervalDal.add(zeroToFifteenOfAgainst);

				TimeInterval sixteenToThirtyOfAgainst = TimeInterval.builder()
						.total(responseItem.getGoals().getAgainst().getMinute().getSixteenToThirty().getTotal())
						.percentage(
								responseItem.getGoals().getAgainst().getMinute().getSixteenToThirty().getPercentage())
						.timeInterval(EnumForTimeInterval.SIXTEEN_TO_THIRTY).build();
				sixteenToThirtyOfAgainst = timeIntervalDal.add(sixteenToThirtyOfAgainst);

				TimeInterval thirtyOneToFortyFiveOfAgainst = TimeInterval.builder()
						.total(responseItem.getGoals().getAgainst().getMinute().getThirtyOneToFortyFive().getTotal())
						.percentage(responseItem.getGoals().getAgainst().getMinute().getThirtyOneToFortyFive()
								.getPercentage())
						.timeInterval(EnumForTimeInterval.THIRTY_ONE_TO_FORTY_FIVE).build();
				thirtyOneToFortyFiveOfAgainst = timeIntervalDal.add(thirtyOneToFortyFiveOfAgainst);

				TimeInterval fortySixToSixtyOfAgainst = TimeInterval.builder()
						.total(responseItem.getGoals().getAgainst().getMinute().getFortySixToSixty().getTotal())
						.percentage(
								responseItem.getGoals().getAgainst().getMinute().getFortySixToSixty().getPercentage())
						.timeInterval(EnumForTimeInterval.FORTY_SIX_TO_SIXTY).build();
				fortySixToSixtyOfAgainst = timeIntervalDal.add(fortySixToSixtyOfAgainst);

				TimeInterval sixtyOneToSeventyFiveOfAgainst = TimeInterval.builder()
						.total(responseItem.getGoals().getAgainst().getMinute().getSixtyOneToSeventyFive().getTotal())
						.percentage(responseItem.getGoals().getAgainst().getMinute().getSixtyOneToSeventyFive()
								.getPercentage())
						.timeInterval(EnumForTimeInterval.SIXTY_ONE_TO_SEVENTY_FIVE).build();
				sixtyOneToSeventyFiveOfAgainst = timeIntervalDal.add(sixtyOneToSeventyFiveOfAgainst);

				TimeInterval seventySixToNinetyOfAgainst = TimeInterval.builder()
						.total(responseItem.getGoals().getAgainst().getMinute().getSeventySixToNinety().getTotal())
						.percentage(responseItem.getGoals().getAgainst().getMinute().getSeventySixToNinety()
								.getPercentage())
						.timeInterval(EnumForTimeInterval.SEVENTY_SIX_TO_NINETY).build();
				seventySixToNinetyOfAgainst = timeIntervalDal.add(seventySixToNinetyOfAgainst);

				TimeInterval ninetyOneToOneHundredFiveOfAgainst = TimeInterval.builder()
						.total(responseItem.getGoals().getAgainst().getMinute().getNinetyOneToOneHundredFive()
								.getTotal())
						.percentage(responseItem.getGoals().getAgainst().getMinute().getNinetyOneToOneHundredFive()
								.getPercentage())
						.timeInterval(EnumForTimeInterval.NINETY_ONE_TO_ONE_HUNDRED_FIVE).build();
				ninetyOneToOneHundredFiveOfAgainst = timeIntervalDal.add(ninetyOneToOneHundredFiveOfAgainst);

				TimeInterval oneHundredSixToOneTwentyOfAgainst = TimeInterval.builder()
						.total(responseItem.getGoals().getAgainst().getMinute().getOneHundredSixToOneHundredTwenty()
								.getTotal())
						.percentage(responseItem.getGoals().getAgainst().getMinute()
								.getOneHundredSixToOneHundredTwenty().getPercentage())
						.timeInterval(EnumForTimeInterval.ONE_HUNDRED_SIX_TO_ONE_TWENTY).build();
				oneHundredSixToOneTwentyOfAgainst = timeIntervalDal.add(oneHundredSixToOneTwentyOfAgainst);

				List<TimeInterval> timeIntervalListOfAgainst = new ArrayList<TimeInterval>();
				timeIntervalListOfAgainst.add(zeroToFifteenOfAgainst);
				timeIntervalListOfAgainst.add(sixteenToThirtyOfAgainst);
				timeIntervalListOfAgainst.add(thirtyOneToFortyFiveOfAgainst);
				timeIntervalListOfAgainst.add(fortySixToSixtyOfAgainst);
				timeIntervalListOfAgainst.add(sixtyOneToSeventyFiveOfAgainst);
				timeIntervalListOfAgainst.add(seventySixToNinetyOfAgainst);
				timeIntervalListOfAgainst.add(ninetyOneToOneHundredFiveOfAgainst);
				timeIntervalListOfAgainst.add(oneHundredSixToOneTwentyOfAgainst);

				Minute minuteOfAgainst = Minute.builder().timeIntervals(timeIntervalListOfAgainst).build();
				minuteOfAgainst = minuteDal.add(minuteOfAgainst);

				TotalAndAverage totalofAgainst = TotalAndAverage.builder()
						.home(responseItem.getGoals().getAgainst().getTotal().getHome())
						.away(responseItem.getGoals().getAgainst().getTotal().getAway())
						.total(responseItem.getGoals().getAgainst().getTotal().getTotal())
						.totalAndAverage(EnumForTotalAndAverage.total).build();
				totalofAgainst = totalAndAverageDal.add(totalofAgainst);

				TotalAndAverage averageOfAgainst = TotalAndAverage.builder()
						.home(new BigDecimal(responseItem.getGoals().getAgainst().getAverage().getHome()))
						.away(new BigDecimal(responseItem.getGoals().getAgainst().getAverage().getAway()))
						.total(new BigDecimal(responseItem.getGoals().getAgainst().getAverage().getTotal()))
						.totalAndAverage(EnumForTotalAndAverage.average).build();
				averageOfAgainst = totalAndAverageDal.add(averageOfAgainst);

				List<TotalAndAverage> totalAndAverageOfAgainst = new ArrayList<TotalAndAverage>();
				totalAndAverageOfAgainst.add(totalofAgainst);
				totalAndAverageOfAgainst.add(averageOfAgainst);

				ForAndAgainstOfGoals forAndAgainstOfGoalsOfAgainst = ForAndAgainstOfGoals.builder()
						.minute(minuteOfAgainst).totalAndAverage(totalAndAverageOfAgainst)
						.ForAndAgainst(EnumForForAndAgainst.Against).build();
				forAndAgainstOfGoalsOfAgainst = forAndAgainstOfGoalsDal.add(forAndAgainstOfGoalsOfAgainst);

				// EndRegion Against

				List<ForAndAgainstOfGoals> forAndAgainstOfGoalsList = new ArrayList<ForAndAgainstOfGoals>();
				forAndAgainstOfGoalsList.add(forAndAgainstOfGoalsOfFor);
				forAndAgainstOfGoalsList.add(forAndAgainstOfGoalsOfAgainst);

				GoalsOfStatistics goalsOfStatistics = GoalsOfStatistics.builder()
						.forAndAgainstOfGoals(forAndAgainstOfGoalsList).build();
				goalsOfStatistics = goalsOfStatisticsDal.add(goalsOfStatistics);
				// EndRegion Goals

				// Region Biggest

				ForAndAgainstOfBiggest forAndAgainstOfBiggestOfFor = ForAndAgainstOfBiggest.builder()
						.home(responseItem.getBiggest().getGoals().getFors().getHome())
						.away(responseItem.getBiggest().getGoals().getFors().getAway())
						.forAndAgainst(EnumForForAndAgainst.For).build();
				forAndAgainstOfBiggestOfFor = forAndAgainstOfBiggestDal.add(forAndAgainstOfBiggestOfFor);

				ForAndAgainstOfBiggest forAndAgainstOfBiggestOfAgainst = ForAndAgainstOfBiggest.builder()
						.home(responseItem.getBiggest().getGoals().getAgainst().getHome())
						.away(responseItem.getBiggest().getGoals().getAgainst().getAway())
						.forAndAgainst(EnumForForAndAgainst.Against).build();
				forAndAgainstOfBiggestOfAgainst = forAndAgainstOfBiggestDal.add(forAndAgainstOfBiggestOfAgainst);

				List<ForAndAgainstOfBiggest> forAndAgainstOfBiggestList = new ArrayList<ForAndAgainstOfBiggest>();
				forAndAgainstOfBiggestList.add(forAndAgainstOfBiggestOfFor);
				forAndAgainstOfBiggestList.add(forAndAgainstOfBiggestOfAgainst);

				GoalsOfBiggest goalsOfBiggest = GoalsOfBiggest.builder()
						.forAndAgainstOfBiggest(forAndAgainstOfBiggestList).build();
				goalsOfBiggest = goalsOfBiggestDal.add(goalsOfBiggest);

				WinsAndLosesOfBiggest winsAndLosesOfBiggestForWins = WinsAndLosesOfBiggest.builder()
						.home(responseItem.getBiggest().getWins().getHome())
						.away(responseItem.getBiggest().getWins().getAway()).winsAndLoses(EnumForWinsAndLoses.Wins)
						.build();
				winsAndLosesOfBiggestForWins = winsAndLosesOfBiggestDal.add(winsAndLosesOfBiggestForWins);

				WinsAndLosesOfBiggest winsAndLosesOfBiggestForLoses = WinsAndLosesOfBiggest.builder()
						.home(responseItem.getBiggest().getLoses().getHome())
						.away(responseItem.getBiggest().getLoses().getAway()).winsAndLoses(EnumForWinsAndLoses.Loses)
						.build();
				winsAndLosesOfBiggestForLoses = winsAndLosesOfBiggestDal.add(winsAndLosesOfBiggestForLoses);

				List<WinsAndLosesOfBiggest> winsAndLosesOfBiggestList = new ArrayList<WinsAndLosesOfBiggest>();
				winsAndLosesOfBiggestList.add(winsAndLosesOfBiggestForWins);
				winsAndLosesOfBiggestList.add(winsAndLosesOfBiggestForLoses);

				StreakOfBiggest streakOfBiggest = StreakOfBiggest.builder()
						.wins(responseItem.getBiggest().getStreak().getWins())
						.draws(responseItem.getBiggest().getStreak().getDraws())
						.loses(responseItem.getBiggest().getStreak().getLoses()).build();
				streakOfBiggest = streakOfBiggestDal.add(streakOfBiggest);

				BiggestOfStatistics biggestOfStatistics = BiggestOfStatistics.builder().streakOfBiggest(streakOfBiggest)
						.winsAndLosesOfBiggest(winsAndLosesOfBiggestList).goalsOfBiggest(goalsOfBiggest).build();
				biggestOfStatistics = biggestOfStatisticsDal.add(biggestOfStatistics);

				// EndRegion Biggest

				// Region CleanSheet

				CleanSheetOfStatistics cleanSheet = CleanSheetOfStatistics.builder()
						.home(responseItem.getClean_sheet().getHome()).away(responseItem.getClean_sheet().getAway())
						.total(responseItem.getClean_sheet().getTotal()).build();
				cleanSheet = cleanSheetOfStatisticsDal.add(cleanSheet);

				// EndRegion CleanSheet

				// Region FailedToScore

				FailedToScoreOfStatistics failedToScore = FailedToScoreOfStatistics.builder()
						.home(responseItem.getFailed_to_score().getHome())
						.away(responseItem.getFailed_to_score().getAway())
						.total(responseItem.getFailed_to_score().getTotal()).build();
				failedToScore = failedToScoreOfStatisticsDal.add(failedToScore);

				// EndRegion FailedToScore

				// Region Penalty

				// Region Scored
				ScoredAndMissedOfPenalty scoredOfPenalty = ScoredAndMissedOfPenalty.builder()
						.total(responseItem.penalty.getScored().getTotal())
						.percentage(responseItem.penalty.getScored().getPercentage())
						.scoredAndMissedOfPenalty(EnumForScoredAndMissed.Scored).build();
				scoredOfPenalty = scoredAndMissedOfPenaltyDal.add(scoredOfPenalty);

				// EndRegion Scored

				// Region Missed
				ScoredAndMissedOfPenalty missedOfPenalty = ScoredAndMissedOfPenalty.builder()
						.total(responseItem.penalty.getMissed().getTotal())
						.percentage(responseItem.penalty.getMissed().getPercentage())
						.scoredAndMissedOfPenalty(EnumForScoredAndMissed.Missed).build();
				missedOfPenalty = scoredAndMissedOfPenaltyDal.add(missedOfPenalty);

				// EndRegion Missed

				List<ScoredAndMissedOfPenalty> scoredAndMissedOfPenaltyList = new ArrayList();
				scoredAndMissedOfPenaltyList.add(scoredOfPenalty);
				scoredAndMissedOfPenaltyList.add(missedOfPenalty);

				PenaltyOfStatistics penaltyOfStatistics = PenaltyOfStatistics.builder()
						.scoredAndMissedOfPenalty(scoredAndMissedOfPenaltyList).total(responseItem.penalty.getTotal())
						.build();
				penaltyOfStatistics = penaltyOfStatisticsDal.add(penaltyOfStatistics);
				// EndRegion Penalty

				// Region Lineups

				List<LineupOfStatistics> lineupOfStatisticsList = new ArrayList();

				for (LineupOfStatisticsScheduledDto responseLineup : responseItem.getLineups()) {
					LineupOfStatistics lineupOfStatistics = LineupOfStatistics.builder()
							.played(responseLineup.getPlayed()).formation(responseLineup.getFormation()).build();
					lineupOfStatistics = lineupOfStatisticsDal.add(lineupOfStatistics);
					lineupOfStatisticsList.add(lineupOfStatistics);
				}

				// EndRegion LineUps

				// Region Cards

				// Region Yellow
				TimeInterval zeroToFifteenOfYellow = TimeInterval.builder()
						.total(responseItem.getCards().getYellow().getZeroToFifteen().getTotal())
						.percentage(responseItem.getCards().getYellow().getZeroToFifteen().getPercentage())
						.timeInterval(EnumForTimeInterval.ZERO_TO_FIFTEEN).build();
				zeroToFifteenOfYellow = timeIntervalDal.add(zeroToFifteenOfYellow);

				TimeInterval sixteenToThirtyOfYellow = TimeInterval.builder()
						.total(responseItem.getCards().getYellow().getSixteenToThirty().getTotal())
						.percentage(responseItem.getCards().getYellow().getSixteenToThirty().getPercentage())
						.timeInterval(EnumForTimeInterval.SIXTEEN_TO_THIRTY).build();
				sixteenToThirtyOfYellow = timeIntervalDal.add(sixteenToThirtyOfYellow);

				TimeInterval thirtyOneToFortyFiveOfYellow = TimeInterval.builder()
						.total(responseItem.getCards().getYellow().getThirtyOneToFortyFive().getTotal())
						.percentage(responseItem.getCards().getYellow().getThirtyOneToFortyFive().getPercentage())
						.timeInterval(EnumForTimeInterval.THIRTY_ONE_TO_FORTY_FIVE).build();
				thirtyOneToFortyFiveOfYellow = timeIntervalDal.add(thirtyOneToFortyFiveOfYellow);

				TimeInterval fortySixToSixtyOfYellow = TimeInterval.builder()
						.total(responseItem.getCards().getYellow().getFortySixToSixty().getTotal())
						.percentage(responseItem.getCards().getYellow().getFortySixToSixty().getPercentage())
						.timeInterval(EnumForTimeInterval.FORTY_SIX_TO_SIXTY).build();
				fortySixToSixtyOfYellow = timeIntervalDal.add(fortySixToSixtyOfYellow);

				TimeInterval sixtyOneToSeventyFiveOfYellow = TimeInterval.builder()
						.total(responseItem.getCards().getYellow().getSixtyOneToSeventyFive().getTotal())
						.percentage(responseItem.getCards().getYellow().getSixtyOneToSeventyFive().getPercentage())
						.timeInterval(EnumForTimeInterval.SIXTY_ONE_TO_SEVENTY_FIVE).build();
				sixtyOneToSeventyFiveOfYellow = timeIntervalDal.add(sixtyOneToSeventyFiveOfYellow);

				TimeInterval seventySixToNinetyOfYellow = TimeInterval.builder()
						.total(responseItem.getCards().getYellow().getSeventySixToNinety().getTotal())
						.percentage(responseItem.getCards().getYellow().getSeventySixToNinety().getPercentage())
						.timeInterval(EnumForTimeInterval.SEVENTY_SIX_TO_NINETY).build();
				seventySixToNinetyOfYellow = timeIntervalDal.add(seventySixToNinetyOfYellow);

				TimeInterval ninetyOneToOneHundredFiveOfYellow = TimeInterval.builder()
						.total(responseItem.getCards().getYellow().getNinetyOneToOneHundredFive().getTotal())
						.percentage(responseItem.getCards().getYellow().getNinetyOneToOneHundredFive().getPercentage())
						.timeInterval(EnumForTimeInterval.NINETY_ONE_TO_ONE_HUNDRED_FIVE).build();
				ninetyOneToOneHundredFiveOfYellow = timeIntervalDal.add(ninetyOneToOneHundredFiveOfYellow);

				TimeInterval oneHundredSixToOneTwentyOfYellow = TimeInterval.builder()
						.total(responseItem.getCards().getYellow().getOneHundredSixToOneHundredTwenty().getTotal())
						.percentage(responseItem.getCards().getYellow().getOneHundredSixToOneHundredTwenty()
								.getPercentage())
						.timeInterval(EnumForTimeInterval.ONE_HUNDRED_SIX_TO_ONE_TWENTY).build();
				oneHundredSixToOneTwentyOfYellow = timeIntervalDal.add(oneHundredSixToOneTwentyOfYellow);

				List<TimeInterval> timeIntervalListOfYellow = new ArrayList<TimeInterval>();
				timeIntervalListOfYellow.add(zeroToFifteenOfYellow);
				timeIntervalListOfYellow.add(sixteenToThirtyOfYellow);
				timeIntervalListOfYellow.add(thirtyOneToFortyFiveOfYellow);
				timeIntervalListOfYellow.add(fortySixToSixtyOfYellow);
				timeIntervalListOfYellow.add(sixtyOneToSeventyFiveOfYellow);
				timeIntervalListOfYellow.add(seventySixToNinetyOfYellow);
				timeIntervalListOfYellow.add(ninetyOneToOneHundredFiveOfYellow);
				timeIntervalListOfYellow.add(oneHundredSixToOneTwentyOfYellow);

				// EndRegion Yellow

				// Region Red
				TimeInterval zeroToFifteenOfRed = TimeInterval.builder()
						.total(responseItem.getCards().getRed().getZeroToFifteen().getTotal())
						.percentage(responseItem.getCards().getRed().getZeroToFifteen().getPercentage())
						.timeInterval(EnumForTimeInterval.ZERO_TO_FIFTEEN).build();
				zeroToFifteenOfRed = timeIntervalDal.add(zeroToFifteenOfRed);

				TimeInterval sixteenToThirtyOfRed = TimeInterval.builder()
						.total(responseItem.getCards().getRed().getSixteenToThirty().getTotal())
						.percentage(responseItem.getCards().getRed().getSixteenToThirty().getPercentage())
						.timeInterval(EnumForTimeInterval.SIXTEEN_TO_THIRTY).build();
				sixteenToThirtyOfRed = timeIntervalDal.add(sixteenToThirtyOfRed);

				TimeInterval thirtyOneToFortyFiveOfRed = TimeInterval.builder()
						.total(responseItem.getCards().getRed().getThirtyOneToFortyFive().getTotal())
						.percentage(responseItem.getCards().getRed().getThirtyOneToFortyFive().getPercentage())
						.timeInterval(EnumForTimeInterval.THIRTY_ONE_TO_FORTY_FIVE).build();
				thirtyOneToFortyFiveOfRed = timeIntervalDal.add(thirtyOneToFortyFiveOfRed);

				TimeInterval fortySixToSixtyOfRed = TimeInterval.builder()
						.total(responseItem.getCards().getRed().getFortySixToSixty().getTotal())
						.percentage(responseItem.getCards().getRed().getFortySixToSixty().getPercentage())
						.timeInterval(EnumForTimeInterval.FORTY_SIX_TO_SIXTY).build();
				fortySixToSixtyOfRed = timeIntervalDal.add(fortySixToSixtyOfRed);

				TimeInterval sixtyOneToSeventyFiveOfRed = TimeInterval.builder()
						.total(responseItem.getCards().getRed().getSixtyOneToSeventyFive().getTotal())
						.percentage(responseItem.getCards().getRed().getSixtyOneToSeventyFive().getPercentage())
						.timeInterval(EnumForTimeInterval.SIXTY_ONE_TO_SEVENTY_FIVE).build();
				sixtyOneToSeventyFiveOfRed = timeIntervalDal.add(sixtyOneToSeventyFiveOfRed);

				TimeInterval seventySixToNinetyOfRed = TimeInterval.builder()
						.total(responseItem.getCards().getRed().getSeventySixToNinety().getTotal())
						.percentage(responseItem.getCards().getRed().getSeventySixToNinety().getPercentage())
						.timeInterval(EnumForTimeInterval.SEVENTY_SIX_TO_NINETY).build();
				seventySixToNinetyOfRed = timeIntervalDal.add(seventySixToNinetyOfRed);

				TimeInterval ninetyOneToOneHundredFiveOfRed = TimeInterval.builder()
						.total(responseItem.getCards().getRed().getNinetyOneToOneHundredFive().getTotal())
						.percentage(responseItem.getCards().getRed().getNinetyOneToOneHundredFive().getPercentage())
						.timeInterval(EnumForTimeInterval.NINETY_ONE_TO_ONE_HUNDRED_FIVE).build();
				ninetyOneToOneHundredFiveOfRed = timeIntervalDal.add(ninetyOneToOneHundredFiveOfRed);

				TimeInterval oneHundredSixToOneTwentyOfRed = TimeInterval.builder()
						.total(responseItem.getCards().getRed().getOneHundredSixToOneHundredTwenty().getTotal())
						.percentage(
								responseItem.getCards().getRed().getOneHundredSixToOneHundredTwenty().getPercentage())
						.timeInterval(EnumForTimeInterval.ONE_HUNDRED_SIX_TO_ONE_TWENTY).build();
				oneHundredSixToOneTwentyOfRed = timeIntervalDal.add(oneHundredSixToOneTwentyOfRed);

				List<TimeInterval> timeIntervalListOfRed = new ArrayList<TimeInterval>();
				timeIntervalListOfRed.add(zeroToFifteenOfRed);
				timeIntervalListOfRed.add(sixteenToThirtyOfRed);
				timeIntervalListOfRed.add(thirtyOneToFortyFiveOfRed);
				timeIntervalListOfRed.add(fortySixToSixtyOfRed);
				timeIntervalListOfRed.add(sixtyOneToSeventyFiveOfRed);
				timeIntervalListOfRed.add(seventySixToNinetyOfRed);
				timeIntervalListOfRed.add(ninetyOneToOneHundredFiveOfRed);
				timeIntervalListOfRed.add(oneHundredSixToOneTwentyOfRed);

				// EndRegion Red

				YellowAndRedOfCards redCards = YellowAndRedOfCards.builder().timeIntervals(timeIntervalListOfRed)
						.yellowAndRed(EnumForYellowAndRed.Red).build();
				redCards = yellowAndRedOfCardsDal.add(redCards);

				YellowAndRedOfCards yellowCards = YellowAndRedOfCards.builder().timeIntervals(timeIntervalListOfYellow)
						.yellowAndRed(EnumForYellowAndRed.Yellow).build();
				yellowCards = yellowAndRedOfCardsDal.add(yellowCards);

				List<YellowAndRedOfCards> yellowandredList = new ArrayList<YellowAndRedOfCards>();
				yellowandredList.add(yellowCards);
				yellowandredList.add(redCards);

				CardsOfStatistics cards = CardsOfStatistics.builder().yellowAndRedOfCards(yellowandredList).build();
				cards = cardsOfStatisticsDal.add(cards);
				// EndRegion Cards

				League league = leagueDal.getById(responseItem.getLeague().getId());

				StatisticForTeam statisticForTeam = StatisticForTeam.builder().fixtureOfStatistics(fixtureOfStatistics)
						.league(league).form(responseItem.getForm())
						.team(teamDal.getById(responseItem.getTeam().getId()))
						.season(seasonDal.getByLeagueAndYear(league, responseItem.getLeague().getSeason()))
						.goalsOfStatistics(goalsOfStatistics).biggestOfStatistics(biggestOfStatistics)
						.cleanSheetOfStatistics(cleanSheet).failedToScoreOfStatistics(failedToScore)
						.penaltyOfStatistics(penaltyOfStatistics).LineupOfStatistics(lineupOfStatisticsList)
						.cardsOfStatistics(cards).year(seasonValue).build();

				statisticForTeam = statisticForTeamDal.add(statisticForTeam);
			}

		}
		return exchange.getBody();

	}

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	private static class StatisticsData {

		private LeagueForStatisticsScheduledDto league;

		private String form;

		private FixtureForStatisticsScheduledDto fixtures;

		private TeamForStatisticsScheduledDto team;

		private GoalsForStatisticsScheduledDto goals;

		private BiggestForStatisticsScheduledDto biggest;

		private CleanSheetForStatisticsScheduledDto clean_sheet;

		private FailedToScoreOfStatisticsScheduledDto failed_to_score;

		private PenaltyForStatisticsScheduledDto penalty;

		private List<LineupOfStatisticsScheduledDto> lineups;

		private CardsForStatisticsScheduledDto cards;

	}
}
