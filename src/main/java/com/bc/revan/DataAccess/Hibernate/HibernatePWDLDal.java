package com.bc.revan.DataAccess.Hibernate;

import org.springframework.stereotype.Repository;

import com.bc.revan.DataAccess.IPWDLDal;
import com.bc.revan.DataAccess.Base.GenericRepository;
import com.bc.revan.Entities.PWDLFixture;

@Repository
public class HibernatePWDLDal extends GenericRepository<PWDLFixture> implements IPWDLDal {

}
