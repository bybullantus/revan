package com.bc.revan.DataAccess;

import java.util.List;

import com.bc.revan.DataAccess.Base.IBaseRepository;
import com.bc.revan.Entities.WeeklyMatch;
import com.bc.revan.Entities.Enums.EnumForStatus;


public interface IWeeklyMatchDal extends IBaseRepository<WeeklyMatch>{
	 List<WeeklyMatch> getByFixture(long fixtureId);	 
	 List<WeeklyMatch> getByStatus(EnumForStatus status);
	 List<String> groupByRound ();
	 List<Integer> groupByYear();
	 List<Long> groupByWeekNumber();
	 List<WeeklyMatch> getBySeasonAndWeek(int year,long week);

}
