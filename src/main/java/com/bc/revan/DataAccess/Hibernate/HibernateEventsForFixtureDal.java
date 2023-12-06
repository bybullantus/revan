package com.bc.revan.DataAccess.Hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bc.revan.DataAccess.IEventsForFixtureDal;
import com.bc.revan.DataAccess.Base.GenericRepository;
import com.bc.revan.Entities.EventsForFixture;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
@Repository
public class HibernateEventsForFixtureDal extends GenericRepository<EventsForFixture> implements IEventsForFixtureDal{

	@Transactional
	@Override
	public List<EventsForFixture> getByFixture(long fixtureId) {
		Session session = entityManager.unwrap(Session.class);
		HibernateCriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<EventsForFixture> criteria = builder.createQuery(EventsForFixture.class);
		Root<EventsForFixture> events = criteria.from(EventsForFixture.class);
		criteria.select(events);		
		criteria.where(builder.equal(events.get("fixture").get("id"), fixtureId));
		
		TypedQuery<EventsForFixture> query = session.createQuery(criteria);
		List<EventsForFixture> a = query.getResultList();
		if (a.size() <= 0)
			return null;
		return query.getResultList();
	}

}
