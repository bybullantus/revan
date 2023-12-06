package com.bc.revan.Business;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.bc.revan.Business.Base.IBaseService;
import com.bc.revan.Entities.Squad;

public interface ISquadService extends IBaseService<Squad> {
	CompletableFuture<Squad> getByTeamId(long teamId);
}
