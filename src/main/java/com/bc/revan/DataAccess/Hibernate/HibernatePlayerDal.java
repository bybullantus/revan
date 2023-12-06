package com.bc.revan.DataAccess.Hibernate;

import org.hibernate.Session;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bc.revan.DataAccess.IPlayerDal;
import com.bc.revan.DataAccess.Base.GenericRepository;
import com.bc.revan.Entities.Player;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;


@Repository
public class HibernatePlayerDal extends GenericRepository<Player> implements IPlayerDal {

	@Transactional
	@Override
	public Player getByIdAndSeason(long playerId, int year) {
		Session session = entityManager.unwrap(Session.class);
		HibernateCriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Player> criteria = builder.createQuery(Player.class);
		Root<Player> players = criteria.from(Player.class);
		
        criteria.select(players);
		criteria.where(builder.and(builder.equal(players.get("id"), playerId),
						builder.equal(players.get("statistics").get("season").get("year"), year)));
       
		TypedQuery<Player> query = session.createQuery(criteria);
		try {
			return query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}
	
}
