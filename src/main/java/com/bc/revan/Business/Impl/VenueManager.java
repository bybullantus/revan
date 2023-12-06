package com.bc.revan.Business.Impl;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.bc.revan.Business.IVenueService;
import com.bc.revan.Business.Base.GenericManager;
import com.bc.revan.DataAccess.ITeamDal;
import com.bc.revan.DataAccess.IVenueDal;
import com.bc.revan.Entities.Team;
import com.bc.revan.Entities.Venue;

import jakarta.transaction.Transactional;

@Service
public class VenueManager  extends GenericManager<Venue,IVenueDal> implements IVenueService {

	
	@Override
	@Async
	public CompletableFuture<List<Venue>> getByName(String name) {
		return CompletableFuture.completedFuture( genericDal.getByName(name));
	}

}
