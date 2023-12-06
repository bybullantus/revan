package com.bc.revan.DataAccess.Hibernate;


import org.springframework.stereotype.Repository;

import com.bc.revan.DataAccess.ICardsOfStatisticsForPlayersDal;
import com.bc.revan.DataAccess.Base.GenericRepository;
import com.bc.revan.Entities.CardsOfStatisticsForPlayers;

@Repository
public class HibernateCardsOfStatisticsForPlayersDal extends GenericRepository<CardsOfStatisticsForPlayers> implements ICardsOfStatisticsForPlayersDal {

	

}
