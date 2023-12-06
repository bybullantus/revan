package com.bc.revan.DataAccess.Hibernate;

import org.springframework.stereotype.Repository;

import com.bc.revan.DataAccess.IPenaltyOfStatisticsForPlayersDal;
import com.bc.revan.DataAccess.Base.GenericRepository;
import com.bc.revan.Entities.PenaltyOfStatisticsForPlayers;

@Repository
public class HibernatePenaltyOfStatisticsForPlayersDal extends GenericRepository<PenaltyOfStatisticsForPlayers>
		implements IPenaltyOfStatisticsForPlayersDal {

}
