package com.bc.revan.DataAccess.Hibernate;

import org.springframework.stereotype.Repository;

import com.bc.revan.DataAccess.ITeamsMainNodeOfFixtureDal;
import com.bc.revan.DataAccess.Base.GenericRepository;
import com.bc.revan.Entities.TeamsMainNodeOfFixture;
@Repository
public class HibernateTeamsMainNodeOfFixtureDal extends GenericRepository<TeamsMainNodeOfFixture>
		implements ITeamsMainNodeOfFixtureDal {

}
