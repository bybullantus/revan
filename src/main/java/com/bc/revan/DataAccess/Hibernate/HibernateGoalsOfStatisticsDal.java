package com.bc.revan.DataAccess.Hibernate;

import org.springframework.stereotype.Repository;

import com.bc.revan.DataAccess.IGoalsOfStatisticsDal;
import com.bc.revan.DataAccess.Base.GenericRepository;
import com.bc.revan.Entities.GoalsOfStatistics;

@Repository
public class HibernateGoalsOfStatisticsDal extends GenericRepository<GoalsOfStatistics> implements IGoalsOfStatisticsDal {

	

}
