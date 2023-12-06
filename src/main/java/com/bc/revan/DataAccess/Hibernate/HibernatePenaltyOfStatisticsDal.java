package com.bc.revan.DataAccess.Hibernate;

import org.springframework.stereotype.Repository;

import com.bc.revan.DataAccess.IPenaltyOfStatisticsDal;
import com.bc.revan.DataAccess.Base.GenericRepository;
import com.bc.revan.Entities.PenaltyOfStatistics;

@Repository
public class HibernatePenaltyOfStatisticsDal extends GenericRepository<PenaltyOfStatistics>
		implements IPenaltyOfStatisticsDal {

}
