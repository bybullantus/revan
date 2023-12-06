package com.bc.revan.DataAccess.Hibernate;

import org.springframework.stereotype.Repository;

import com.bc.revan.DataAccess.IStreakOfBiggestDal;
import com.bc.revan.DataAccess.Base.GenericRepository;
import com.bc.revan.Entities.StreakOfBiggest;
@Repository
public class HibernateStreakOfBiggestDal extends GenericRepository<StreakOfBiggest> implements IStreakOfBiggestDal {

}
