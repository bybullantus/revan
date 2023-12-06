package com.bc.revan.DataAccess.Hibernate;

import org.springframework.stereotype.Repository;

import com.bc.revan.DataAccess.ICardsOfStatisticsDal;
import com.bc.revan.DataAccess.Base.GenericRepository;
import com.bc.revan.Entities.CardsOfStatistics;

@Repository
public class HibernateCardsOfStatisticsDal extends GenericRepository<CardsOfStatistics> implements ICardsOfStatisticsDal {



}
