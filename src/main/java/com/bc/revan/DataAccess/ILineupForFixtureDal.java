package com.bc.revan.DataAccess;

import java.util.List;

import com.bc.revan.DataAccess.Base.IBaseRepository;
import com.bc.revan.Entities.LineupForFixture;

public interface ILineupForFixtureDal extends IBaseRepository<LineupForFixture> {
	List<LineupForFixture> getByFixture(long fixtureId);
}
