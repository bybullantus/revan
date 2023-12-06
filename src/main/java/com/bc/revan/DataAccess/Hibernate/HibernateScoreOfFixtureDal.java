package com.bc.revan.DataAccess.Hibernate;

import org.springframework.stereotype.Repository;

import com.bc.revan.DataAccess.IScoreOfFixtureDal;
import com.bc.revan.DataAccess.Base.GenericRepository;
import com.bc.revan.Entities.ScoreOfFixture;

@Repository
public class HibernateScoreOfFixtureDal extends GenericRepository<ScoreOfFixture> implements IScoreOfFixtureDal {

}
