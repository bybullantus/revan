package com.bc.revan.Business;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.bc.revan.Business.Base.IBaseService;
import com.bc.revan.Entities.Team;

public interface ITeamService extends IBaseService<Team> {
	CompletableFuture<List<Team>> getByName(String name);
	
}
