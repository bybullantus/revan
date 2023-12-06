package com.bc.revan.Business;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.bc.revan.Business.Base.IBaseService;
import com.bc.revan.Entities.Coachs;
import com.bc.revan.Entities.Country;

public interface ICoachService  extends IBaseService<Coachs> {
	CompletableFuture<List<Coachs>> getByTeam(long teamId);
}
