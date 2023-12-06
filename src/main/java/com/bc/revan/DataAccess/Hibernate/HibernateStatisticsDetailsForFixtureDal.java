package com.bc.revan.DataAccess.Hibernate;

import org.springframework.stereotype.Repository;

import com.bc.revan.DataAccess.IStatisticsDetailsForFixtureDal;
import com.bc.revan.DataAccess.Base.GenericRepository;
import com.bc.revan.Entities.StatisticsDetailsForFixture;
@Repository
public class HibernateStatisticsDetailsForFixtureDal extends GenericRepository<StatisticsDetailsForFixture>
		implements IStatisticsDetailsForFixtureDal {

}
