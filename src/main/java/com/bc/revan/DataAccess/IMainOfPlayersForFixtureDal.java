package com.bc.revan.DataAccess;

import java.util.List;

import com.bc.revan.DataAccess.Base.IBaseRepository;
import com.bc.revan.Entities.MainOfPlayersForFixture;

public interface IMainOfPlayersForFixtureDal  extends IBaseRepository<MainOfPlayersForFixture>{
	List<MainOfPlayersForFixture> getByFixture(long fixtureId);
}
