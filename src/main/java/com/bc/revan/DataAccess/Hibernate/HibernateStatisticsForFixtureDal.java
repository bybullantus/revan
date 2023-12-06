package com.bc.revan.DataAccess.Hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;
import org.springframework.stereotype.Repository;

import com.bc.revan.DataAccess.IStatisticsForFixtureDal;
import com.bc.revan.DataAccess.Base.GenericRepository;
import com.bc.revan.Entities.StatisticsForFixture;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
@Repository
public class HibernateStatisticsForFixtureDal extends GenericRepository<StatisticsForFixture>
		implements IStatisticsForFixtureDal {

	@Transactional
	@Override
	public List<StatisticsForFixture> getByFixture(long fixtureId) {
		Session session = entityManager.unwrap(Session.class);
		HibernateCriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<StatisticsForFixture> criteria = builder.createQuery(StatisticsForFixture.class);
		Root<StatisticsForFixture> statistics = criteria.from(StatisticsForFixture.class);
		criteria.select(statistics);
		criteria.where(builder.equal(statistics.get("fixture").get("id"), fixtureId));

		TypedQuery<StatisticsForFixture> query = session.createQuery(criteria);
		List<StatisticsForFixture> a = query.getResultList();
		if (a.size() <= 0)
			return null;
		return query.getResultList();
	}

	@Transactional
	@Override
	public List<StatisticsForFixture> getByFixtureAndTeam(long fixtureId, long teamId) {
		Session session = entityManager.unwrap(Session.class);
		HibernateCriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<StatisticsForFixture> criteria = builder.createQuery(StatisticsForFixture.class);
		Root<StatisticsForFixture> statistics = criteria.from(StatisticsForFixture.class);
		criteria.select(statistics);
		// criteria.where(builder.equal(statistics.get("fixture").get("id"),
		// fixtureId));
		criteria.where(builder.and(builder.equal(statistics.get("fixture").get("id"), fixtureId),
				builder.equal(statistics.get("team").get("id"), teamId)));
		TypedQuery<StatisticsForFixture> query = session.createQuery(criteria);
		List<StatisticsForFixture> a = query.getResultList();
		if (a.size() <= 0)
			return null;
		return query.getResultList();
	}

}
