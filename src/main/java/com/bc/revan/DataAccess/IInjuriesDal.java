package com.bc.revan.DataAccess;

import java.util.List;

import com.bc.revan.DataAccess.Base.IBaseRepository;
import com.bc.revan.Entities.Injuries;

public interface IInjuriesDal extends IBaseRepository<Injuries>{
	List<Injuries> getByFixture(long fixtureId);
}
