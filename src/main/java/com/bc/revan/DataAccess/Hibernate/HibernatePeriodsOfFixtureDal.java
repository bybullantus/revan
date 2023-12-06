package com.bc.revan.DataAccess.Hibernate;

import org.springframework.stereotype.Repository;

import com.bc.revan.DataAccess.IPeriodsOfFixtureDal;
import com.bc.revan.DataAccess.Base.GenericRepository;
import com.bc.revan.Entities.PeriodsOfFixture;

@Repository
public class HibernatePeriodsOfFixtureDal extends GenericRepository<PeriodsOfFixture> implements IPeriodsOfFixtureDal {

}
