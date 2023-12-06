package com.bc.revan.Business.Impl;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.bc.revan.Business.ILineupForFixtureService;
import com.bc.revan.Business.Base.GenericManager;
import com.bc.revan.DataAccess.ILineupForFixtureDal;
import com.bc.revan.Entities.LineupForFixture;

@Service
public class LineupForFixtureManager extends GenericManager<LineupForFixture,ILineupForFixtureDal> implements ILineupForFixtureService {

	@Async
	@Override
	public CompletableFuture<List<LineupForFixture>> getByFixture(long fixtureId) {
		return CompletableFuture.completedFuture(genericDal.getByFixture(fixtureId));
	}
	

}
