package com.bc.revan.Business.Impl;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.bc.revan.Business.IInjuriesService;
import com.bc.revan.Business.Base.GenericManager;
import com.bc.revan.DataAccess.IInjuriesDal;
import com.bc.revan.Entities.Injuries;


@Service
public class InjuriesManager extends GenericManager<Injuries,IInjuriesDal> implements IInjuriesService {

	@Async
	@Override
	public CompletableFuture<List<Injuries>> getByFixture(long fixtureId) {
		return CompletableFuture.completedFuture(genericDal.getByFixture(fixtureId));
	}

}
