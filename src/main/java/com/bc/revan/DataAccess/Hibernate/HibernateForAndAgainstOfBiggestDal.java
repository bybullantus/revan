package com.bc.revan.DataAccess.Hibernate;

import org.springframework.stereotype.Repository;

import com.bc.revan.DataAccess.IForAndAgainstOfBiggestDal;
import com.bc.revan.DataAccess.Base.GenericRepository;
import com.bc.revan.Entities.ForAndAgainstOfBiggest;

@Repository
public class HibernateForAndAgainstOfBiggestDal extends GenericRepository<ForAndAgainstOfBiggest> implements IForAndAgainstOfBiggestDal {

	

}
