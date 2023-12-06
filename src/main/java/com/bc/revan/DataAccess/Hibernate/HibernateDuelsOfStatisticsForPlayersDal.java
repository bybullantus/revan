package com.bc.revan.DataAccess.Hibernate;

import org.springframework.stereotype.Repository;

import com.bc.revan.DataAccess.IDuelsOfStatisticsForPlayersDal;
import com.bc.revan.DataAccess.Base.GenericRepository;
import com.bc.revan.Entities.DuelsOfStatisticsForPlayers;

@Repository
public class HibernateDuelsOfStatisticsForPlayersDal extends GenericRepository<DuelsOfStatisticsForPlayers>
		implements IDuelsOfStatisticsForPlayersDal {

}
