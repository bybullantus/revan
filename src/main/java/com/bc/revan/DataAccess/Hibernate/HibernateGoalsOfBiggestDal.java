package com.bc.revan.DataAccess.Hibernate;

import org.springframework.stereotype.Repository;

import com.bc.revan.DataAccess.IGoalsOfBiggestDal;
import com.bc.revan.DataAccess.Base.GenericRepository;
import com.bc.revan.Entities.GoalsOfBiggest;
@Repository
public class HibernateGoalsOfBiggestDal extends GenericRepository<GoalsOfBiggest> implements IGoalsOfBiggestDal {

	
}
