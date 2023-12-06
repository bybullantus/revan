package com.bc.revan.DataAccess.Hibernate;

import org.springframework.stereotype.Repository;

import com.bc.revan.DataAccess.IGamesForStatisticsOfPlayerDal;
import com.bc.revan.DataAccess.Base.GenericRepository;
import com.bc.revan.Entities.GamesForStatisticsOfPlayer;

@Repository
public class HibernateGamesForStatisticsOfPlayerDal extends GenericRepository<GamesForStatisticsOfPlayer>
		implements IGamesForStatisticsOfPlayerDal {

}
