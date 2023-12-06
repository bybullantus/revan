package com.bc.revan.Business;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.bc.revan.Business.Base.IBaseService;
import com.bc.revan.Entities.League;

public interface ILeagueService extends IBaseService<League> {
	CompletableFuture<List<League>> getByName(String name);
}
