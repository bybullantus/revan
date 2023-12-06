package com.bc.revan.DataAccess.Hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;
import org.springframework.stereotype.Repository;

import com.bc.revan.DataAccess.IWeeklyMatchDal;
import com.bc.revan.DataAccess.Base.GenericRepository;
import com.bc.revan.Entities.FixtureMainNode;
import com.bc.revan.Entities.WeeklyMatch;
import com.bc.revan.Entities.Enums.EnumForStatus;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;

@Repository
public class HibernateWeeklyMatchDal extends GenericRepository<WeeklyMatch> implements IWeeklyMatchDal {

	@Transactional
	@Override
	public List<WeeklyMatch> getByFixture(long fixtureId) {

		Session session = entityManager.unwrap(Session.class);
		HibernateCriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<WeeklyMatch> criteria = builder.createQuery(WeeklyMatch.class);
		Root<WeeklyMatch> table = criteria.from(WeeklyMatch.class);
		criteria.select(table);
		criteria.where(builder.and(builder.equal(table.get("fixtureMainNode").get("fixture").get("id"), fixtureId),
				builder.equal(table.get("isDeleted"), false)));
		TypedQuery<WeeklyMatch> query = session.createQuery(criteria);
		List<WeeklyMatch> a = query.getResultList();
		if (a.size() <= 0)
			return null;
		return query.getResultList();
	}

	@Transactional
	@Override
	public List<WeeklyMatch> getByStatus(EnumForStatus status) {
		Session session = entityManager.unwrap(Session.class);
		HibernateCriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<WeeklyMatch> criteria = builder.createQuery(WeeklyMatch.class);
		Root<WeeklyMatch> table = criteria.from(WeeklyMatch.class);
		criteria.select(table);

		if (status.equals(EnumForStatus.NULL))
			criteria.where(
					builder.and(builder.isNull(table.get("status")), builder.equal(table.get("isDeleted"), false)));
		else
			criteria.where(builder.and(builder.equal(table.get("status"), status),
					builder.equal(table.get("isDeleted"), false)));
		
		TypedQuery<WeeklyMatch> query = session.createQuery(criteria);
		List<WeeklyMatch> a = query.getResultList();
		if (a.size() <= 0)
			return null;

		return query.getResultList();
	}

	@Transactional
	@Override
	public List<String> groupByRound() {
		Session session = entityManager.unwrap(Session.class);
		HibernateCriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<String> criteria = builder.createQuery(String.class);
		Root<WeeklyMatch> table = criteria.from(WeeklyMatch.class);
		criteria.multiselect(table.get("fixtureMainNode").get("round"));
		// criteria.where(builder.and(builder.equal(table.get("status"), status),
		// builder.equal(table.get("isDeleted"), false)));
		criteria.groupBy(table.get("fixtureMainNode").get("round"));
		TypedQuery<String> query = session.createQuery(criteria);
		List<String> a = query.getResultList();
		if (a.size() <= 0)
			return null;

		return query.getResultList();
	}

	@Transactional
	@Override
	public List<Integer> groupByYear() {
		Session session = entityManager.unwrap(Session.class);
		HibernateCriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Integer> criteria = builder.createQuery(Integer.class);
		Root<WeeklyMatch> table = criteria.from(WeeklyMatch.class);
		criteria.multiselect(table.get("fixtureMainNode").get("season").get("year"));
		// criteria.where(builder.and(builder.equal(table.get("status"), status),
		// builder.equal(table.get("isDeleted"), false)));
		criteria.groupBy(table.get("fixtureMainNode").get("season").get("year"));
		TypedQuery<Integer> query = session.createQuery(criteria);
		List<Integer> a = query.getResultList();
		if (a.size() <= 0)
			return null;

		return query.getResultList();
	}

	@Transactional
	@Override
	public List<Long> groupByWeekNumber() {
		Session session = entityManager.unwrap(Session.class);
		HibernateCriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<WeeklyMatch> table = criteria.from(WeeklyMatch.class);
		criteria.multiselect(table.get("weekNumber"));
		// criteria.where(builder.and(builder.equal(table.get("status"), status),
		// builder.equal(table.get("isDeleted"), false)));
		criteria.groupBy(table.get("weekNumber"));
		TypedQuery<Long> query = session.createQuery(criteria);
		List<Long> a = query.getResultList();
		if (a.size() <= 0)
			return null;

		return query.getResultList();
	}

	@Transactional
	@Override
	public List<WeeklyMatch> getBySeasonAndWeek(int year, long week) {
		Session session = entityManager.unwrap(Session.class);
		HibernateCriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<WeeklyMatch> criteria = builder.createQuery(WeeklyMatch.class);
		Root<WeeklyMatch> table = criteria.from(WeeklyMatch.class);
		criteria.select(table);
		criteria.where(
				builder.and(builder.equal(table.get("weekNumber"), week), builder.equal(table.get("isDeleted"), false),
						builder.equal(table.get("fixtureMainNode").get("season").get("year"), year)));
		TypedQuery<WeeklyMatch> query = session.createQuery(criteria);
		List<WeeklyMatch> a = query.getResultList();
		if (a.size() <= 0)
			return null;
		return query.getResultList();
	}
}
