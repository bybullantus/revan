package com.bc.revan.DataAccess;

import java.util.List;

import com.bc.revan.DataAccess.Base.IBaseRepository;
import com.bc.revan.Entities.EventsForFixture;

public interface IEventsForFixtureDal extends IBaseRepository<EventsForFixture> {
	List<EventsForFixture> getByFixture(long fixtureId);
}
