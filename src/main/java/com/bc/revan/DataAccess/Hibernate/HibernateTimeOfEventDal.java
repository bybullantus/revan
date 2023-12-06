package com.bc.revan.DataAccess.Hibernate;

import org.springframework.stereotype.Repository;

import com.bc.revan.DataAccess.ITimeOfEventDal;
import com.bc.revan.DataAccess.Base.GenericRepository;
import com.bc.revan.Entities.TimeOfEvent;
@Repository
public class HibernateTimeOfEventDal extends GenericRepository<TimeOfEvent> implements ITimeOfEventDal {

}
