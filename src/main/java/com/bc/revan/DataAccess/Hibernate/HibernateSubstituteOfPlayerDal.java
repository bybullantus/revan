package com.bc.revan.DataAccess.Hibernate;

import org.springframework.stereotype.Repository;

import com.bc.revan.DataAccess.ISubstituteOfPlayerDal;
import com.bc.revan.DataAccess.Base.GenericRepository;
import com.bc.revan.Entities.SubstituteOfPlayer;

@Repository
public class HibernateSubstituteOfPlayerDal extends GenericRepository<SubstituteOfPlayer>
		implements ISubstituteOfPlayerDal {

}
