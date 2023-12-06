package com.bc.revan.DataAccess;

import java.util.List;

import com.bc.revan.DataAccess.Base.IBaseRepository;
import com.bc.revan.Entities.FixtureMainNode;

public interface IFixtureMainNodeDal extends IBaseRepository<FixtureMainNode> {

	FixtureMainNode getByFixture(long fixtureId);

	List<FixtureMainNode> getBySeasonAndLeagueId(int year, long leagueId);

	List<FixtureMainNode> getBySeasonAndH2h(int year, long teamId1, long teamId2);

	List<FixtureMainNode> getBySeasonAndLeagueIdAndH2h(int year, long leagueId, long teamId1, long teamId2);

	List<FixtureMainNode> getBySeasonAndLeagueIdAndHomeTeam(int year, long leagueId, long teamId);

	List<FixtureMainNode> getBySeasonAndLeagueIdAndAwayTeam(int year, long leagueId, long teamId);
	
    List<FixtureMainNode> getBySeasonAndLeagueIdAndAwayTeamWithoutNS(int year, long leagueId, long teamId);
	List<FixtureMainNode> getBySeasonAndLeagueIdAndHomeTeamWithoutNS(int year, long leagueId, long teamId);


	List<FixtureMainNode> getByRound(String round);

	List<FixtureMainNode> getByStatus(String statu);
	
	
}
