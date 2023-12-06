package com.bc.revan.Business;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.bc.revan.Business.Base.IBaseService;
import com.bc.revan.Entities.Injuries;

public interface IInjuriesService extends IBaseService<Injuries>{
	CompletableFuture<List<Injuries>> getByFixture(long fixtureId);
}
