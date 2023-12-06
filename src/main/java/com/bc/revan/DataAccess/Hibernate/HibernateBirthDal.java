package com.bc.revan.DataAccess.Hibernate;


import org.springframework.stereotype.Repository;

import com.bc.revan.DataAccess.IBirthDal;
import com.bc.revan.DataAccess.Base.GenericRepository;
import com.bc.revan.Entities.Birth;


@Repository
public class HibernateBirthDal extends GenericRepository<Birth> implements IBirthDal {

	

}
