package com.bc.revan.DataAccess;

import java.util.List;

import com.bc.revan.DataAccess.Base.IBaseRepository;
import com.bc.revan.Entities.League;
import com.bc.revan.Entities.Season;


public interface ISeasonDal extends IBaseRepository<Season> {
	Season getByLeagueAndYear(League league,int year);
	List<Season> getByLeagueId(long leagueid);
	Season getByYear(int year);
	
}
