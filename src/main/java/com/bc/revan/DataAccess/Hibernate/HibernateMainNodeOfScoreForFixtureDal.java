package com.bc.revan.DataAccess.Hibernate;

import org.springframework.stereotype.Repository;

import com.bc.revan.DataAccess.IMainNodeOfScoreForFixtureDal;
import com.bc.revan.DataAccess.Base.GenericRepository;
import com.bc.revan.Entities.MainNodeOfScoreForFixture;

@Repository
public class HibernateMainNodeOfScoreForFixtureDal extends GenericRepository<MainNodeOfScoreForFixture>
		implements IMainNodeOfScoreForFixtureDal {

}
