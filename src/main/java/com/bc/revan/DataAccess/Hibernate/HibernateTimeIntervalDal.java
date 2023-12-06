package com.bc.revan.DataAccess.Hibernate;

import org.springframework.stereotype.Repository;

import com.bc.revan.DataAccess.ITimeIntervalDal;
import com.bc.revan.DataAccess.Base.GenericRepository;
import com.bc.revan.Entities.TimeInterval;
@Repository
public class HibernateTimeIntervalDal extends GenericRepository<TimeInterval> implements ITimeIntervalDal {

}
