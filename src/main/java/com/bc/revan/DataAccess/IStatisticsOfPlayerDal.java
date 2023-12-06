package com.bc.revan.DataAccess;

import java.util.List;

import com.bc.revan.DataAccess.Base.IBaseRepository;
import com.bc.revan.Entities.StatisticsOfPlayer;

public interface IStatisticsOfPlayerDal extends IBaseRepository<StatisticsOfPlayer>{
	List<StatisticsOfPlayer> getBySeasonAndPlayerId(int year,long playerId);
	List<StatisticsOfPlayer> getBySeasonAndPlayerIdAndLeagueId(int year,long playerId,long leagueId);

}
