package com.bc.revan.DataAccess.Hibernate;


import org.springframework.stereotype.Repository;

import com.bc.revan.DataAccess.IForAndAgainstOfGoalsDal;
import com.bc.revan.DataAccess.Base.GenericRepository;
import com.bc.revan.Entities.ForAndAgainstOfGoals;

@Repository
public class HibernateForAndAgainstOfGoalsDal extends GenericRepository<ForAndAgainstOfGoals> implements IForAndAgainstOfGoalsDal {

	
}
