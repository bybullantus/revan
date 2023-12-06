package com.bc.revan.DataAccess;

import com.bc.revan.DataAccess.Base.IBaseRepository;
import com.bc.revan.Entities.StandingMainNode;
import com.bc.revan.Entities.StatisticForTeam;

public interface IStandingMainNodeDal extends IBaseRepository<StandingMainNode> {
	StandingMainNode getBySeasonAndLeagueId(int year,long leagueId);
	void deleteAllStandingMain();
}
