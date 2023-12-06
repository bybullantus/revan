package com.bc.revan.DataAccess.Hibernate;

import org.springframework.stereotype.Repository;

import com.bc.revan.DataAccess.IStartXIAndSubsOfLineupDal;
import com.bc.revan.DataAccess.Base.GenericRepository;
import com.bc.revan.Entities.StartXIAndSubsOfLineup;

@Repository
public class HibernateStartXIAndSubsOfLineupDal extends GenericRepository<StartXIAndSubsOfLineup>
		implements IStartXIAndSubsOfLineupDal {

}
