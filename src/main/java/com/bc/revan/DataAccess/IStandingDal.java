package com.bc.revan.DataAccess;

import com.bc.revan.DataAccess.Base.IBaseRepository;
import com.bc.revan.Entities.Standing;

public interface IStandingDal extends IBaseRepository<Standing> {
	void deleteAllStanding();

}
