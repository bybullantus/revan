package com.bc.revan.DataAccess.Hibernate;

import org.springframework.stereotype.Repository;

import com.bc.revan.DataAccess.IGoalsOfFixtureDal;
import com.bc.revan.DataAccess.Base.GenericRepository;
import com.bc.revan.Entities.GoalsOfFixture;
@Repository
public class HibernateGoalsOfFixtureDal  extends GenericRepository<GoalsOfFixture> implements IGoalsOfFixtureDal {



}
