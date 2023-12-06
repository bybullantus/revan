package com.bc.revan.Business.Impl;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.bc.revan.Business.ITeamService;
import com.bc.revan.Business.Base.GenericManager;
import com.bc.revan.DataAccess.ITeamDal;
import com.bc.revan.Entities.Team;


@Service
public class TeamManager  extends GenericManager<Team,ITeamDal> implements ITeamService {


	
	@Override
	@Async
	public CompletableFuture<List<Team>> getByName(String name) {
		return CompletableFuture.completedFuture(genericDal.getByName(name));
	}

	

}
