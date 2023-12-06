package com.bc.revan.DataAccess.Hibernate;

import org.springframework.stereotype.Repository;

import com.bc.revan.DataAccess.IGoalsOfStatisticsForPlayersDal;
import com.bc.revan.DataAccess.Base.GenericRepository;
import com.bc.revan.Entities.GoalsOfStatisticsForPlayers;

@Repository
public class HibernateGoalsOfStatisticsForPlayersDal extends GenericRepository<GoalsOfStatisticsForPlayers>
		implements IGoalsOfStatisticsForPlayersDal {

}
