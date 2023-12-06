package com.bc.revan.DataAccess.Hibernate;

import org.springframework.stereotype.Repository;

import com.bc.revan.DataAccess.ITacklesOfStatisticsForPlayersDal;
import com.bc.revan.DataAccess.Base.GenericRepository;
import com.bc.revan.Entities.TacklesOfStatisticsForPlayers;
@Repository
public class HibernateTacklesOfStatisticsForPlayersDal extends GenericRepository<TacklesOfStatisticsForPlayers>
		implements ITacklesOfStatisticsForPlayersDal {

}
