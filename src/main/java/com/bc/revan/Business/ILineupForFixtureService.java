package com.bc.revan.Business;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.bc.revan.Business.Base.IBaseService;
import com.bc.revan.Entities.LineupForFixture;

public interface ILineupForFixtureService extends IBaseService<LineupForFixture>
{
	CompletableFuture<List<LineupForFixture>> getByFixture(long fixtureId);
}
