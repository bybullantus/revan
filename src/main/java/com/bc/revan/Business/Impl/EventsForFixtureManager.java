package com.bc.revan.Business.Impl;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.stereotype.Service;

import com.bc.revan.Business.IEventsForFixtureService;
import com.bc.revan.Business.Base.GenericManager;
import com.bc.revan.DataAccess.IEventsForFixtureDal;
import com.bc.revan.Entities.EventsForFixture;


@Service
public class EventsForFixtureManager extends GenericManager<EventsForFixture,IEventsForFixtureDal> implements IEventsForFixtureService{

	
	@Override
	public CompletableFuture<List<EventsForFixture>> getByFixture(long fixtureId) {
		return CompletableFuture.completedFuture(genericDal.getByFixture(fixtureId));
	}

}
