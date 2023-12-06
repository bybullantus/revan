package com.bc.revan.DataAccess;

import com.bc.revan.DataAccess.Base.IBaseRepository;
import com.bc.revan.Entities.GoalsOfAllHomeAway;

public interface IGoalsOfAllHomeAwayDal extends IBaseRepository<GoalsOfAllHomeAway>{
	void deleteAllGoalsOfAllHomeAway();

}
