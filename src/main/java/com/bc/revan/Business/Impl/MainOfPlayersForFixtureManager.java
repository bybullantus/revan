package com.bc.revan.Business.Impl;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.bc.revan.Business.IMainOfPlayersForFixtureService;
import com.bc.revan.Business.Base.GenericManager;
import com.bc.revan.DataAccess.IMainOfPlayersForFixtureDal;
import com.bc.revan.Entities.MainOfPlayersForFixture;

@Service
public class MainOfPlayersForFixtureManager extends GenericManager<MainOfPlayersForFixture,IMainOfPlayersForFixtureDal> implements IMainOfPlayersForFixtureService {

	
	@Async
	@Override
	public CompletableFuture<List<MainOfPlayersForFixture>> getByFixture(long fixtureId) {
		return CompletableFuture.completedFuture(genericDal.getByFixture(fixtureId));
	}

}
