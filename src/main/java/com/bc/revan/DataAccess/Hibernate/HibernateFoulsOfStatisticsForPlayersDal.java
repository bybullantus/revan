package com.bc.revan.DataAccess.Hibernate;

import org.springframework.stereotype.Repository;

import com.bc.revan.DataAccess.IFoulsOfStatisticsForPlayersDal;
import com.bc.revan.DataAccess.Base.GenericRepository;
import com.bc.revan.Entities.FoulsOfStatisticsForPlayers;
@Repository
public class HibernateFoulsOfStatisticsForPlayersDal extends GenericRepository<FoulsOfStatisticsForPlayers>  implements IFoulsOfStatisticsForPlayersDal{

	

}
