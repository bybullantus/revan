package com.bc.revan.DataAccess.Hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;
import org.springframework.stereotype.Repository;

import com.bc.revan.DataAccess.IVenueDal;
import com.bc.revan.DataAccess.Base.GenericRepository;
import com.bc.revan.Entities.Venue;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
@Repository
public class HibernateVenueDal extends GenericRepository<Venue> implements IVenueDal {

	@Override
	public List<Venue> getByName(String name) {
		Session session = entityManager.unwrap(Session.class);
		HibernateCriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Venue> criteria = builder.createQuery(Venue.class);
		Root<Venue> venues = criteria.from(Venue.class);

		criteria.select(venues);
		criteria.where(builder.equal(venues.get("name"), name));
		TypedQuery<Venue> query = session.createQuery(criteria);
		List<Venue> a = query.getResultList();
		if (a.size() <= 0)
			return null;
		return query.getResultList();
	}

}
