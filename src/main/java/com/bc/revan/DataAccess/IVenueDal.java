package com.bc.revan.DataAccess;

import java.util.List;

import com.bc.revan.DataAccess.Base.IBaseRepository;
import com.bc.revan.Entities.Venue;

public interface IVenueDal extends IBaseRepository<Venue> {
	List<Venue> getByName(String name);
	
}
