package com.bc.revan.DataAccess;


import com.bc.revan.DataAccess.Base.IBaseRepository;
import com.bc.revan.Entities.StatisticForTeam;

public interface IStatisticForTeamDal extends IBaseRepository<StatisticForTeam> {

	StatisticForTeam getBySeasonAndLeagueIdAndTeamId(int year,long leagueId,long teamId);
	
}
