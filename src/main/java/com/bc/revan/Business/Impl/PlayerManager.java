package com.bc.revan.Business.Impl;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.bc.revan.Business.IPlayerService;
import com.bc.revan.Business.Base.GenericManager;
import com.bc.revan.DataAccess.IPlayerDal;
import com.bc.revan.DataAccess.IStatisticsOfPlayerDal;
import com.bc.revan.Entities.Player;
import com.bc.revan.Entities.StatisticsOfPlayer;
@Service
public class PlayerManager  extends GenericManager<Player,IPlayerDal> implements IPlayerService {

	@Autowired
	IStatisticsOfPlayerDal statisticsOfPlayerDal;
	
	@Async
	@Override
	public CompletableFuture<Player> getByIdAndSeason(long playerId, int year) {
		Player player=genericDal.getById(playerId);
		List<StatisticsOfPlayer> statistics = statisticsOfPlayerDal.getBySeasonAndPlayerId(year, playerId);
		player.setStatistics(statistics);
		return CompletableFuture.completedFuture(player);
	}
	
	@Async
	@Override
	public CompletableFuture<Player> getByIdAndSeasonAndLeague(long playerId, int year, long leagueId) {
		Player player=genericDal.getById(playerId);
		List<StatisticsOfPlayer> statistics = statisticsOfPlayerDal.getBySeasonAndPlayerIdAndLeagueId(year, playerId,leagueId);
		player.setStatistics(statistics);
		return CompletableFuture.completedFuture(player);
	}

}
