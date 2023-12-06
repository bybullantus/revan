package com.bc.revan.DataAccess.Hibernate;

import org.springframework.stereotype.Repository;

import com.bc.revan.DataAccess.IPassesOfStatisticsForPlayersDal;
import com.bc.revan.DataAccess.Base.GenericRepository;
import com.bc.revan.Entities.PassesOfStatisticsForPlayers;

@Repository
public class HibernatePassesOfStatisticsForPlayersDal extends GenericRepository<PassesOfStatisticsForPlayers>
		implements IPassesOfStatisticsForPlayersDal {

}
