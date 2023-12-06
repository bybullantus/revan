package com.bc.revan.DataAccess.Hibernate;


import org.springframework.stereotype.Repository;

import com.bc.revan.DataAccess.ICareerOfCoachDal;
import com.bc.revan.DataAccess.Base.GenericRepository;
import com.bc.revan.Entities.CareerOfCoach;

@Repository
public class HibernateCareerOfCoachDal  extends GenericRepository<CareerOfCoach> implements ICareerOfCoachDal {


}
