package com.bc.revan.DataAccess.Hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bc.revan.DataAccess.IMainOfPlayersForFixtureDal;
import com.bc.revan.DataAccess.Base.GenericRepository;
import com.bc.revan.Entities.MainOfPlayersForFixture;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

@Repository
public class HibernateMainOfPlayersForFixtureDal extends GenericRepository<MainOfPlayersForFixture>
		implements IMainOfPlayersForFixtureDal {

	@Transactional
	@Override
	public List<MainOfPlayersForFixture> getByFixture(long fixtureId) {
		Session session = entityManager.unwrap(Session.class);
		HibernateCriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<MainOfPlayersForFixture> criteria = builder.createQuery(MainOfPlayersForFixture.class);
		Root<MainOfPlayersForFixture> players = criteria.from(MainOfPlayersForFixture.class);
		criteria.select(players);
		criteria.where(builder.equal(players.get("fixture").get("id"), fixtureId));
		TypedQuery<MainOfPlayersForFixture> query = session.createQuery(criteria);
		List<MainOfPlayersForFixture> a = query.getResultList();
		if (a.size() <= 0)
			return null;
		return query.getResultList();
	}

}
