package com.bc.revan.Business.Impl;

import java.util.concurrent.CompletableFuture;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.bc.revan.Business.ISquadService;
import com.bc.revan.Business.Base.GenericManager;
import com.bc.revan.DataAccess.ISquadDal;
import com.bc.revan.Entities.Squad;

@Service
public class SquadManager extends GenericManager<Squad, ISquadDal> implements ISquadService {

	@Async
	@Override
	public CompletableFuture<Squad> getByTeamId(long teamId) {
		return CompletableFuture.completedFuture(genericDal.getByTeam(teamId));
	}

}
