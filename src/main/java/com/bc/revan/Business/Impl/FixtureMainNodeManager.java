package com.bc.revan.Business.Impl;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.bc.revan.Business.IFixtureMainNodeService;
import com.bc.revan.Business.Base.GenericManager;
import com.bc.revan.DataAccess.IFixtureMainNodeDal;
import com.bc.revan.Entities.FixtureMainNode;

import jakarta.transaction.Transactional;

@Service
public class FixtureMainNodeManager  extends GenericManager<FixtureMainNode,IFixtureMainNodeDal> implements IFixtureMainNodeService {

	 
	@Async
	@Transactional
	@Override
	public CompletableFuture<List<FixtureMainNode>> getBySeasonAndH2h(int year, long teamId1 , long teamId2) {
		return CompletableFuture.completedFuture(genericDal.getBySeasonAndH2h(year, teamId1, teamId2));
	}
	
	@Async
	@Transactional
	@Override
	public CompletableFuture<List<FixtureMainNode>> getBySeasonAndLeagueIdAndH2h(int year, long leagueId, long teamId1 , long teamId2) {
		return CompletableFuture.completedFuture(genericDal.getBySeasonAndLeagueIdAndH2h(year, leagueId, teamId1, teamId2));
	}
	@Async
	@Transactional
	@Override
	public CompletableFuture<FixtureMainNode> getByFixtureId(long fixtureId) {
		return CompletableFuture.completedFuture(genericDal.getByFixture(fixtureId));

	}
	@Async
	@Transactional
	@Override
	public CompletableFuture<List<FixtureMainNode>> getByStatus(String status) {
		return CompletableFuture.completedFuture(genericDal.getByStatus(status));
	}

}
