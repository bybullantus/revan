package com.bc.revan.Business.Impl;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.bc.revan.Business.ICoachService;
import com.bc.revan.Business.Base.GenericManager;
import com.bc.revan.DataAccess.ICoachsDal;
import com.bc.revan.Entities.Coachs;


@Service
public class CoachManager extends GenericManager<Coachs,ICoachsDal> implements ICoachService{
    
	@Async
    @Override
	public CompletableFuture<List<Coachs>> getByTeam(long teamId) {
		return CompletableFuture.completedFuture(genericDal.getByTeam(teamId));
	}
}
