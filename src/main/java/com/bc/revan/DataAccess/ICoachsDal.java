package com.bc.revan.DataAccess;

import java.util.List;

import com.bc.revan.DataAccess.Base.IBaseRepository;
import com.bc.revan.Entities.Coachs;

public interface ICoachsDal extends IBaseRepository<Coachs> {
	List<Coachs> getByTeam(long teamId);
}
