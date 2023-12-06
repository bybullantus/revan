package com.bc.revan.DataAccess;

import java.util.List;

import com.bc.revan.DataAccess.Base.IBaseRepository;
import com.bc.revan.Entities.Team;

public interface ITeamDal extends IBaseRepository<Team> {
	List<Team> getByName(String name);
	List<Team> getByCountry(String country);
	Long getMaxId();
}
