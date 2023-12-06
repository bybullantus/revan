package com.bc.revan.DataAccess.Hibernate;

import org.springframework.stereotype.Repository;

import com.bc.revan.DataAccess.IStatusOfFixtureDal;
import com.bc.revan.DataAccess.Base.GenericRepository;
import com.bc.revan.Entities.StatusOfFixture;
@Repository
public class HibernateStatusOfFixtureDal extends GenericRepository<StatusOfFixture> implements IStatusOfFixtureDal {

}
