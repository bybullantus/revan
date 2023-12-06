package com.bc.revan.DataAccess.Hibernate;

import org.springframework.stereotype.Repository;

import com.bc.revan.DataAccess.ILineupOfStatisticsDal;
import com.bc.revan.DataAccess.Base.GenericRepository;
import com.bc.revan.Entities.LineupOfStatistics;

@Repository
public class HibernateLineupOfStatisticsDal extends GenericRepository<LineupOfStatistics>
		implements ILineupOfStatisticsDal {

}
