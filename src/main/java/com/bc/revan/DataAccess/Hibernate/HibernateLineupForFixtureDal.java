package com.bc.revan.DataAccess.Hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bc.revan.DataAccess.ILineupForFixtureDal;
import com.bc.revan.DataAccess.Base.GenericRepository;
import com.bc.revan.Entities.LineupForFixture;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

@Repository
public class HibernateLineupForFixtureDal  extends GenericRepository<LineupForFixture> implements ILineupForFixtureDal{
	
	@Transactional
	@Override
	public List<LineupForFixture> getByFixture(long fixtureId) {
		Session session = entityManager.unwrap(Session.class);
		HibernateCriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<LineupForFixture> criteria = builder.createQuery(LineupForFixture.class);
		Root<LineupForFixture> lineups = criteria.from(LineupForFixture.class);
		criteria.select(lineups);		
		criteria.where(builder.equal(lineups.get("fixture").get("id"), fixtureId));	
		TypedQuery<LineupForFixture> query = session.createQuery(criteria);
		List<LineupForFixture> a = query.getResultList();
		if (a.size() <= 0)
			return null;
		return query.getResultList();
	}

}
