package com.bc.revan.DataAccess.Hibernate;

import org.springframework.stereotype.Repository;

import com.bc.revan.DataAccess.IColorOfLineupDal;
import com.bc.revan.DataAccess.Base.GenericRepository;
import com.bc.revan.Entities.ColorOfLineup;

@Repository
public class HibernateColorOfLineupDal extends GenericRepository<ColorOfLineup>  implements IColorOfLineupDal {
	
	

}
