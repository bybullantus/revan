package com.bc.revan.DataAccess.Hibernate;


import org.springframework.stereotype.Repository;

import com.bc.revan.DataAccess.IFixtureDal;
import com.bc.revan.DataAccess.Base.GenericRepository;
import com.bc.revan.Entities.Fixture;
@Repository
public class HibernateFixtureDal extends GenericRepository<Fixture>  implements IFixtureDal {

	

}
