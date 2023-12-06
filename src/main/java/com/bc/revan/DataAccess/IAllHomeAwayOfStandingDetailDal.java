package com.bc.revan.DataAccess;

import com.bc.revan.DataAccess.Base.IBaseRepository;
import com.bc.revan.Entities.AllHomeAwayOfStandingDetail;

public interface IAllHomeAwayOfStandingDetailDal extends IBaseRepository<AllHomeAwayOfStandingDetail> {
	void deleteAllHomeAwayOfStandingDetail();

}
