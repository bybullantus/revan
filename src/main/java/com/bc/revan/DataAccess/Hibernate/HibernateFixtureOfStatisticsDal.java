package com.bc.revan.DataAccess.Hibernate;

import org.springframework.stereotype.Repository;

import com.bc.revan.DataAccess.IFixtureOfStatisticsDal;
import com.bc.revan.DataAccess.Base.GenericRepository;
import com.bc.revan.Entities.FixtureOfStatistics;
@Repository
public class HibernateFixtureOfStatisticsDal extends GenericRepository<FixtureOfStatistics> implements IFixtureOfStatisticsDal {
	
	

}
