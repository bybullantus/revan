package com.bc.revan.Business;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.bc.revan.Business.Base.IBaseService;
import com.bc.revan.Entities.StatisticsForFixture;

public interface IStatisticForFixtureService extends IBaseService<StatisticsForFixture>{
	CompletableFuture<List<StatisticsForFixture>> getByFixture(long fixtureId);
	CompletableFuture<List<StatisticsForFixture>> getByFixtureAndTeam(long fixtureId,long teamId);
}
