package com.bc.revan.DataAccess.Hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bc.revan.DataAccess.ILeagueDal;
import com.bc.revan.DataAccess.Base.GenericRepository;
import com.bc.revan.Entities.League;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

@Repository
public class HibernateLeagueDal extends GenericRepository<League> implements ILeagueDal {

	@Transactional
	@Override
	public List<League> getByName(String name) {
		Session session = entityManager.unwrap(Session.class);
		HibernateCriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<League> criteria = builder.createQuery(League.class);
		Root<League> leagues = criteria.from(League.class);

		criteria.select(leagues);
		criteria.where(builder.equal(leagues.get("name"), name));
		TypedQuery<League> query = session.createQuery(criteria);
		List<League> a = query.getResultList();
		if (a.size() <= 0)
			return null;
		return query.getResultList();
	}
}
