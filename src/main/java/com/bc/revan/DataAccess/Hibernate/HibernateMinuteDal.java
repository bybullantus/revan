package com.bc.revan.DataAccess.Hibernate;

import org.springframework.stereotype.Repository;

import com.bc.revan.DataAccess.IMinuteDal;
import com.bc.revan.DataAccess.Base.GenericRepository;
import com.bc.revan.Entities.Minute;

@Repository
public class HibernateMinuteDal extends GenericRepository<Minute> implements IMinuteDal {

}
