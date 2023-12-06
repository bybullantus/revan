package com.bc.revan.Business;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.bc.revan.Business.Base.IBaseService;
import com.bc.revan.Entities.EventsForFixture;

public interface IEventsForFixtureService extends IBaseService<EventsForFixture>{
	CompletableFuture<List<EventsForFixture>> getByFixture(long fixtureId);
}
