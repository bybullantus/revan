package com.bc.revan.DataAccess.Hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bc.revan.DataAccess.ISeasonDal;
import com.bc.revan.DataAccess.Base.GenericRepository;
import com.bc.revan.Entities.League;
import com.bc.revan.Entities.Season;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

@Repository
public class HibernateSeasonDal extends GenericRepository<Season> implements ISeasonDal {

	@Override
	@Transactional
	public Season getByLeagueAndYear(League league, int year) {
		Session session = entityManager.unwrap(Session.class);
		HibernateCriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Season> criteria = builder.createQuery(Season.class);
		Root<Season> leagues = criteria.from(Season.class);

		criteria.select(leagues);
		criteria.where(builder.and(builder.equal(leagues.get("league").get("id"), league.getId()),
				builder.equal(leagues.get("year"), year)));
		// criteria.where(builder.equal(leagues.get("league"),
		// league)).where(builder.equal(leagues.get("year"),year));
		TypedQuery<Season> query = session.createQuery(criteria);
		List<Season> a = query.getResultList();
		if (a.size() <= 0)
			return null;
		return a.get(0);
	}

	@Override
	@Transactional
	public List<Season> getByLeagueId(long leagueid) {
		Session session = entityManager.unwrap(Session.class);
		HibernateCriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Season> criteria = builder.createQuery(Season.class);
		Root<Season> seasons = criteria.from(Season.class);

		criteria.select(seasons).where(builder.equal(seasons.get("leagueid"), leagueid));
		TypedQuery<Season> query = session.createQuery(criteria);
		return query.getResultList();
	}

	@Transactional
	@Override
	public Season getByYear(int year) {
		Session session = entityManager.unwrap(Session.class);
		HibernateCriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Season> criteria = builder.createQuery(Season.class);
		Root<Season> seasons = criteria.from(Season.class);

		criteria.select(seasons).where(builder.equal(seasons.get("year"), year));
		TypedQuery<Season> query = session.createQuery(criteria);
		return query.getSingleResult();
	}

}
