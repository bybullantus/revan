package com.bc.revan.Business.Impl;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.bc.revan.Business.IStatisticForTeamService;
import com.bc.revan.Business.Base.GenericManager;
import com.bc.revan.DataAccess.IStatisticForTeamDal;
import com.bc.revan.DataAccess.IStatisticsForFixtureDal;
import com.bc.revan.Entities.BiggestOfStatistics;
import com.bc.revan.Entities.CardsOfStatistics;
import com.bc.revan.Entities.CleanSheetOfStatistics;
import com.bc.revan.Entities.FailedToScoreOfStatistics;
import com.bc.revan.Entities.FixtureOfStatistics;
import com.bc.revan.Entities.GoalsOfStatistics;
import com.bc.revan.Entities.LineupOfStatistics;
import com.bc.revan.Entities.PenaltyOfStatistics;
import com.bc.revan.Entities.StatisticForTeam;
import com.bc.revan.Entities.StatisticsForFixture;

import jakarta.transaction.Transactional;

@Service
public class StatisticForTeamManager extends GenericManager<StatisticForTeam,IStatisticForTeamDal> implements IStatisticForTeamService {


	@Override
	@Async
	public CompletableFuture<StatisticForTeam> getBySeasonAndLeagueIdAndTeamId(int year, int leagueId, int teamId) {
		return CompletableFuture.completedFuture(genericDal.getBySeasonAndLeagueIdAndTeamId(year, leagueId, teamId));
	}

	
	@Override
	@Async
	public CompletableFuture<FixtureOfStatistics> getFixtureOfStatisticsBySeasonAndLeagueIdAndTeamId(int year,
			int leagueId, int teamId) {
		return CompletableFuture.completedFuture(genericDal.getBySeasonAndLeagueIdAndTeamId(year, leagueId, teamId).getFixtureOfStatistics());
	}

	
	@Override
	@Async
	public CompletableFuture<BiggestOfStatistics> getBiggestOfStatisticsBySeasonAndLeagueIdAndTeamId(int year,
			int leagueId, int teamId) {
		return CompletableFuture.completedFuture(genericDal.getBySeasonAndLeagueIdAndTeamId(year, leagueId, teamId).getBiggestOfStatistics());

	}

	
	@Override
	@Async
	public CompletableFuture<GoalsOfStatistics> getGoalsOfStatisticsBySeasonAndLeagueIdAndTeamId(int year, int leagueId,
			int teamId) {
		return CompletableFuture.completedFuture(genericDal.getBySeasonAndLeagueIdAndTeamId(year, leagueId, teamId).getGoalsOfStatistics());

	}

	
	@Override
	@Async
	public CompletableFuture<String> getFormOfStatisticsBySeasonAndLeagueIdAndTeamId(int year, int leagueId,
			int teamId) {
		return CompletableFuture.completedFuture(genericDal.getBySeasonAndLeagueIdAndTeamId(year, leagueId, teamId).getForm());

	}

	
	@Override
	@Async
	public CompletableFuture<CleanSheetOfStatistics> getCleanSheetOfStatisticsBySeasonAndLeagueIdAndTeamId(int year,
			int leagueId, int teamId) {
		return CompletableFuture.completedFuture(genericDal.getBySeasonAndLeagueIdAndTeamId(year, leagueId, teamId).getCleanSheetOfStatistics());

	}

	
	@Override
	@Async
	public CompletableFuture<FailedToScoreOfStatistics> getFailedToScoreOfStatisticsBySeasonAndLeagueIdAndTeamId(
			int year, int leagueId, int teamId) {
		return CompletableFuture.completedFuture(genericDal.getBySeasonAndLeagueIdAndTeamId(year, leagueId, teamId).getFailedToScoreOfStatistics());

	}

	
	@Override
	@Async
	public CompletableFuture<PenaltyOfStatistics> getPenaltyOfStatisticsBySeasonAndLeagueIdAndTeamId(int year,
			int leagueId, int teamId) {
		return CompletableFuture.completedFuture(genericDal.getBySeasonAndLeagueIdAndTeamId(year, leagueId, teamId).getPenaltyOfStatistics());

	}

	
	@Override
	@Async
	public CompletableFuture<List<LineupOfStatistics>> getLineupOfStatisticsBySeasonAndLeagueIdAndTeamId(int year,
			int leagueId, int teamId) {
		return CompletableFuture.completedFuture(genericDal.getBySeasonAndLeagueIdAndTeamId(year, leagueId, teamId).getLineupOfStatistics());

	}

	
	@Override
	@Async
	public CompletableFuture<CardsOfStatistics> getCardsOfStatisticsBySeasonAndLeagueIdAndTeamId(int year, int leagueId,
			int teamId) {
		return CompletableFuture.completedFuture(genericDal.getBySeasonAndLeagueIdAndTeamId(year, leagueId, teamId).getCardsOfStatistics());

	}

}
