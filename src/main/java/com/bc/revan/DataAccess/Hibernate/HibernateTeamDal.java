package com.bc.revan.DataAccess.Hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bc.revan.DataAccess.ITeamDal;
import com.bc.revan.DataAccess.Base.GenericRepository;
import com.bc.revan.Entities.Team;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
@Repository
public class HibernateTeamDal extends GenericRepository<Team> implements ITeamDal {

	@Transactional
	@Override
	public List<Team> getByName(String name) {
		Session session = entityManager.unwrap(Session.class);
		HibernateCriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Team> criteria = builder.createQuery(Team.class);
		Root<Team> teams = criteria.from(Team.class);

		criteria.select(teams);
		criteria.where(builder.equal(teams.get("name"), name));
		TypedQuery<Team> query = session.createQuery(criteria);
		List<Team> a = query.getResultList();
		if (a.size() <= 0)
			return null;
		return query.getResultList();
	}

	@Transactional
	@Override
	public Long getMaxId() {
		/*
		 * Session session = entityManager.unwrap(Session.class);
		 * HibernateCriteriaBuilder builder = session.getCriteriaBuilder();
		 * CriteriaQuery<Team> criteria = builder.createQuery(Team.class); Root<Team>
		 * teams = criteria.from(Team.class);
		 * 
		 * criteria.select(builder.desc(teams.get("id")));
		 * //criteria.where(builder.equal(teams.get("id"), id)); TypedQuery<Team> query
		 * = session.createQuery(criteria); long maxId =
		 * query.getSingleResult().getId(); if (maxId == 0) return null; return maxId;
		 */

		Session session = entityManager.unwrap(Session.class);
		Query<Long> query = session.createQuery("SELECT MAX(id) FROM Team", Long.class);
		Long maxId = query.uniqueResult();

		return maxId;
	}
	@Transactional
	@Override
	public List<Team> getByCountry(String country) {
		Session session = entityManager.unwrap(Session.class);
		HibernateCriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Team> criteria = builder.createQuery(Team.class);
		Root<Team> teams = criteria.from(Team.class);

		criteria.select(teams);
		criteria.where(builder.and(builder.equal(teams.get("country"), country),builder.equal(teams.get("national"),false)) );
		TypedQuery<Team> query = session.createQuery(criteria);
		List<Team> a = query.getResultList();
		if (a.size() <= 0)
			return null;
		return query.getResultList();
	}

}
