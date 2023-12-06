package com.bc.revan.Business.Impl;

import java.util.concurrent.CompletableFuture;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.bc.revan.Business.IStandingMainNodeService;
import com.bc.revan.Business.Base.GenericManager;
import com.bc.revan.DataAccess.IStandingMainNodeDal;
import com.bc.revan.Entities.StandingMainNode;


@Service
public class StandingMainNodeManager extends GenericManager<StandingMainNode,IStandingMainNodeDal> implements IStandingMainNodeService {

	@Override
	@Async
	public CompletableFuture<StandingMainNode> getBySeasonAndLeagueId(int year, int leagueId) {
		return CompletableFuture.completedFuture(genericDal.getBySeasonAndLeagueId(year, leagueId));
	}

}
