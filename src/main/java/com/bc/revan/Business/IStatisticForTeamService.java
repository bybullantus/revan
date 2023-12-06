package com.bc.revan.Business;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.bc.revan.Business.Base.IBaseService;
import com.bc.revan.Entities.BiggestOfStatistics;
import com.bc.revan.Entities.CardsOfStatistics;
import com.bc.revan.Entities.CleanSheetOfStatistics;
import com.bc.revan.Entities.FailedToScoreOfStatistics;
import com.bc.revan.Entities.FixtureOfStatistics;
import com.bc.revan.Entities.GoalsOfStatistics;
import com.bc.revan.Entities.LineupOfStatistics;
import com.bc.revan.Entities.PenaltyOfStatistics;
import com.bc.revan.Entities.StatisticForTeam;

public interface IStatisticForTeamService extends IBaseService<StatisticForTeam> {
	CompletableFuture<StatisticForTeam> getBySeasonAndLeagueIdAndTeamId(int year,int leagueId,int teamId);
	CompletableFuture<FixtureOfStatistics> getFixtureOfStatisticsBySeasonAndLeagueIdAndTeamId(int year,int leagueId,int teamId);
	CompletableFuture<BiggestOfStatistics> getBiggestOfStatisticsBySeasonAndLeagueIdAndTeamId(int year,int leagueId,int teamId);
	CompletableFuture<GoalsOfStatistics> getGoalsOfStatisticsBySeasonAndLeagueIdAndTeamId(int year,int leagueId,int teamId);
	CompletableFuture<String> getFormOfStatisticsBySeasonAndLeagueIdAndTeamId(int year,int leagueId,int teamId);
	CompletableFuture<CleanSheetOfStatistics> getCleanSheetOfStatisticsBySeasonAndLeagueIdAndTeamId(int year,int leagueId,int teamId);
	CompletableFuture<FailedToScoreOfStatistics> getFailedToScoreOfStatisticsBySeasonAndLeagueIdAndTeamId(int year,int leagueId,int teamId);
	CompletableFuture<PenaltyOfStatistics> getPenaltyOfStatisticsBySeasonAndLeagueIdAndTeamId(int year,int leagueId,int teamId);
	CompletableFuture<List<LineupOfStatistics>>  getLineupOfStatisticsBySeasonAndLeagueIdAndTeamId(int year,int leagueId,int teamId);
	CompletableFuture<CardsOfStatistics> getCardsOfStatisticsBySeasonAndLeagueIdAndTeamId(int year,int leagueId,int teamId);


}
