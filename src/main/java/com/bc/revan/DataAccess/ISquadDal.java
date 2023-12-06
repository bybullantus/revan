package com.bc.revan.DataAccess;

import com.bc.revan.DataAccess.Base.IBaseRepository;
import com.bc.revan.Entities.Squad;

public interface ISquadDal extends IBaseRepository<Squad>{
	Squad getByTeam(long teamId);
}
