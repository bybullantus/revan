package com.bc.revan.Business;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.bc.revan.Business.Base.IBaseService;
import com.bc.revan.Entities.FixtureMainNode;

public interface IFixtureMainNodeService extends IBaseService<FixtureMainNode> {
	CompletableFuture<FixtureMainNode> getByFixtureId(long fixtureId);
	CompletableFuture<List<FixtureMainNode>> getBySeasonAndH2h(int year, long teamId1 , long teamId2);
	CompletableFuture<List<FixtureMainNode>> getBySeasonAndLeagueIdAndH2h(int year,long leagueId, long teamId1 , long teamId2);
	CompletableFuture<List<FixtureMainNode>> getByStatus(String status);
}
