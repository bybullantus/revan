package com.bc.revan.Business;

import java.util.concurrent.CompletableFuture;

import com.bc.revan.Business.Base.IBaseService;
import com.bc.revan.Entities.Player;

public interface IPlayerService extends IBaseService<Player>{
	CompletableFuture<Player> getByIdAndSeason(long playerId, int year);
	CompletableFuture<Player> getByIdAndSeasonAndLeague(long playerId, int year,long leagueId);
}
