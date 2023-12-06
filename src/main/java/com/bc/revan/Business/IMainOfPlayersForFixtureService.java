package com.bc.revan.Business;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.bc.revan.Business.Base.IBaseService;
import com.bc.revan.Entities.MainOfPlayersForFixture;

public interface IMainOfPlayersForFixtureService extends IBaseService<MainOfPlayersForFixture>{
	CompletableFuture<List<MainOfPlayersForFixture>> getByFixture(long fixtureId);
}
