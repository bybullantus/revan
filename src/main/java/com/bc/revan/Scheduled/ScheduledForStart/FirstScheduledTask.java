package com.bc.revan.Scheduled.ScheduledForStart;

import java.text.SimpleDateFormat;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.bc.revan.Entegration.ICoachScheduledService;
import com.bc.revan.Entegration.ICountryScheduledService;
import com.bc.revan.Entegration.IEventsForFixtureScheduledService;
import com.bc.revan.Entegration.IFixtureScheduledService;
import com.bc.revan.Entegration.IInjuriesScheduledService;
import com.bc.revan.Entegration.ILeagueScheduledService;
import com.bc.revan.Entegration.ILineupForFixtureScheduledService;
import com.bc.revan.Entegration.IPlayersForFixtureScheduledService;
import com.bc.revan.Entegration.IPlayersScheduledService;
import com.bc.revan.Entegration.ISquadsForPlayersScheduledService;
import com.bc.revan.Entegration.IStandingScheduledService;
import com.bc.revan.Entegration.IStatisticForTeamScheduledService;
import com.bc.revan.Entegration.IStatisticsForFixtureScheduledService;
import com.bc.revan.Entegration.ITeamScheduledService;

@Component
public class FirstScheduledTask {
	@Autowired
	ICountryScheduledService countryScheduledService;
	@Autowired
	ILeagueScheduledService leagueScheduledService;
	@Autowired
	ITeamScheduledService teamScheduledService;
	@Autowired
	ICoachScheduledService coachScheduledService;
	@Autowired
	ISquadsForPlayersScheduledService squadsForPlayersScheduledService;
	@Autowired
	IStatisticForTeamScheduledService statisticForTeamScheduledService;
	@Autowired
	IFixtureScheduledService fixtureScheduledService;
	@Autowired
	IStatisticsForFixtureScheduledService statisticsForFixtureScheduledService;
	@Autowired
	IEventsForFixtureScheduledService eventsForFixtureScheduledService;
	@Autowired
	IStandingScheduledService standingScheduledService;
	@Autowired
	IInjuriesScheduledService injuriesScheduledService;
	@Autowired
	ILineupForFixtureScheduledService lineupForFixtureScheduledService;
	@Autowired
	IPlayersForFixtureScheduledService playersForFixtureScheduledService;
	@Autowired
	IPlayersScheduledService playerScheduledService;

	static final SimpleDateFormat dateformat = new SimpleDateFormat("HH:mm:ss");

   // @Scheduled(fixedRate = 1000 * 60 * 60 * 24 * 365)
	public void getFirtsTask() throws InterruptedException, ExecutionException {

		countryScheduledService.getCountries();
		leagueScheduledService.getLeagues();
		teamScheduledService.getTeams();
		squadsForPlayersScheduledService.getSquads();
		statisticForTeamScheduledService.getStatisticsForTeam();
		fixtureScheduledService.getFixture();//burası
		statisticsForFixtureScheduledService.getStatisticsForFixture();
		eventsForFixtureScheduledService.getEventsForFixture();
		standingScheduledService.getStanding();
		injuriesScheduledService.getInjuries();
		coachScheduledService.getCoach();
		lineupForFixtureScheduledService.getLineups();
		playersForFixtureScheduledService.getPlayers();
		playerScheduledService.getPlayers();
		
		
		/*
		 * standing
		 * fixture
		 * weekly
		 * statuyü düzeltecek olan scheduled
		 * */

	}
}
