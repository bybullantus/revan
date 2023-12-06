package com.bc.revan.DataAccess;

import com.bc.revan.DataAccess.Base.IBaseRepository;
import com.bc.revan.Entities.StandingDetail;

public interface IStandingDetailDal extends IBaseRepository<StandingDetail>{
	void deleteAllStandingDetail();

}
