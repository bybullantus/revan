package com.bc.revan.DataAccess.Hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bc.revan.DataAccess.IInjuriesDal;
import com.bc.revan.DataAccess.Base.GenericRepository;
import com.bc.revan.Entities.Injuries;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

@Repository
public class HibernateInjuriesDal extends GenericRepository<Injuries> implements IInjuriesDal {

	@Transactional
	@Override
	public List<Injuries> getByFixture(long fixtureId) {
		Session session = entityManager.unwrap(Session.class);
		HibernateCriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Injuries> criteria = builder.createQuery(Injuries.class);
		Root<Injuries> injuries = criteria.from(Injuries.class);
		criteria.select(injuries);
		criteria.where(builder.equal(injuries.get("fixture").get("id"), fixtureId));
		TypedQuery<Injuries> query = session.createQuery(criteria);
		List<Injuries> a = query.getResultList();
		if (a.size() <= 0)
			return null;
		return query.getResultList();
	}

}
