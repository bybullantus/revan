package com.bc.revan.DataAccess.Hibernate;

import org.springframework.stereotype.Repository;

import com.bc.revan.DataAccess.IPlayersForFixtureDal;
import com.bc.revan.DataAccess.Base.GenericRepository;
import com.bc.revan.Entities.PlayersForFixture;

@Repository
public class HibernatePlayersForFixtureDal extends GenericRepository<PlayersForFixture>
		implements IPlayersForFixtureDal {

}
