package com.bc.revan.DataAccess.Hibernate;

import org.springframework.stereotype.Repository;

import com.bc.revan.DataAccess.IYellowAndRedOfCardsDal;
import com.bc.revan.DataAccess.Base.GenericRepository;
import com.bc.revan.Entities.YellowAndRedOfCards;
@Repository
public  class  HibernateYellowAndRedOfCardsDal extends GenericRepository<YellowAndRedOfCards>
		implements IYellowAndRedOfCardsDal {

}
