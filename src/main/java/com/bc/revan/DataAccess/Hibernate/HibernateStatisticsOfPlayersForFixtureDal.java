package com.bc.revan.DataAccess.Hibernate;

import org.springframework.stereotype.Repository;

import com.bc.revan.DataAccess.IStatisticsOfPlayersForFixtureDal;
import com.bc.revan.DataAccess.Base.GenericRepository;
import com.bc.revan.Entities.StatisticsOfPlayersForFixture;
@Repository
public class HibernateStatisticsOfPlayersForFixtureDal extends GenericRepository<StatisticsOfPlayersForFixture>
		implements IStatisticsOfPlayersForFixtureDal {

}
