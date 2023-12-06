package com.bc.revan.Business.Impl;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.bc.revan.Business.ILeagueService;
import com.bc.revan.Business.Base.GenericManager;
import com.bc.revan.DataAccess.ILeagueDal;
import com.bc.revan.Entities.League;

@Service
public class LeagueManager extends GenericManager<League,ILeagueDal> implements ILeagueService {

	

	@Override
	@Async
	public CompletableFuture<List<League>> getByName(String name) {
		
		return CompletableFuture.completedFuture(genericDal.getByName(name));
	}

}
