package com.bc.revan.DataAccess;

import java.util.List;

import com.bc.revan.DataAccess.Base.IBaseRepository;
import com.bc.revan.Entities.League;

public interface ILeagueDal extends IBaseRepository<League> {
	List<League> getByName(String name);
}
