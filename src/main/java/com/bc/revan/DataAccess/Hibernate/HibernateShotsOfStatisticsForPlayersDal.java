package com.bc.revan.DataAccess.Hibernate;

import org.springframework.stereotype.Repository;

import com.bc.revan.DataAccess.IShotsOfStatisticsForPlayersDal;
import com.bc.revan.DataAccess.Base.GenericRepository;
import com.bc.revan.Entities.ShotsOfStatisticsForPlayers;

@Repository
public class HibernateShotsOfStatisticsForPlayersDal extends GenericRepository<ShotsOfStatisticsForPlayers>
		implements IShotsOfStatisticsForPlayersDal {

}
