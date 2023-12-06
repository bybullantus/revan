package com.bc.revan.DataAccess.Hibernate;

import org.springframework.stereotype.Repository;

import com.bc.revan.DataAccess.IWinsAndLosesOfBiggestDal;
import com.bc.revan.DataAccess.Base.GenericRepository;
import com.bc.revan.Entities.WinsAndLosesOfBiggest;
@Repository
public class HibernateWinsAndLosesOfBiggestDal extends GenericRepository<WinsAndLosesOfBiggest>
		implements IWinsAndLosesOfBiggestDal {

}
