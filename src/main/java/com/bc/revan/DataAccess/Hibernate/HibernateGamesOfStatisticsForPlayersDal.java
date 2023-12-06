package com.bc.revan.DataAccess.Hibernate;

import org.springframework.stereotype.Repository;

import com.bc.revan.DataAccess.IGamesOfStatisticsForPlayersDal;
import com.bc.revan.DataAccess.Base.GenericRepository;
import com.bc.revan.Entities.GamesOfStatisticsForPlayers;
@Repository
public class HibernateGamesOfStatisticsForPlayersDal extends GenericRepository<GamesOfStatisticsForPlayers>  implements IGamesOfStatisticsForPlayersDal {

	
}
