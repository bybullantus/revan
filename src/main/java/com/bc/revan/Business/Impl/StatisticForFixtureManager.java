package com.bc.revan.Business.Impl;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.bc.revan.Business.IStatisticForFixtureService;
import com.bc.revan.Business.Base.GenericManager;
import com.bc.revan.DataAccess.IStatisticsForFixtureDal;
import com.bc.revan.Entities.StatisticsForFixture;


@Service
public class StatisticForFixtureManager extends GenericManager<StatisticsForFixture,IStatisticsForFixtureDal> implements IStatisticForFixtureService{


	@Async
	@Override
	public CompletableFuture<List<StatisticsForFixture>> getByFixture(long fixtureId) {
		
		return CompletableFuture.completedFuture(genericDal.getByFixture(fixtureId));
	}

	@Async
	@Override
	public CompletableFuture<List<StatisticsForFixture>> getByFixtureAndTeam(long fixtureId, long teamId) {
		return CompletableFuture.completedFuture(genericDal.getByFixtureAndTeam(fixtureId,teamId));
	}

}
