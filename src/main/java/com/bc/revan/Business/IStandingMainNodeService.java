package com.bc.revan.Business;

import java.util.concurrent.CompletableFuture;

import com.bc.revan.Business.Base.IBaseService;
import com.bc.revan.Entities.Standing;
import com.bc.revan.Entities.StandingMainNode;

public interface IStandingMainNodeService extends IBaseService<StandingMainNode> {
	CompletableFuture<StandingMainNode> getBySeasonAndLeagueId(int year,int leagueId);
}
