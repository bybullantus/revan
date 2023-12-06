package com.bc.revan.DataAccess;

import java.util.List;

import com.bc.revan.DataAccess.Base.IBaseRepository;
import com.bc.revan.Entities.StatisticsForFixture;

public interface IStatisticsForFixtureDal extends IBaseRepository<StatisticsForFixture>{
	List<StatisticsForFixture> getByFixture(long fixtureId);
	List<StatisticsForFixture> getByFixtureAndTeam(long fixtureId , long teamId);
}
