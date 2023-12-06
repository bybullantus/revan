package com.bc.revan.DataAccess.Hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;
import org.springframework.stereotype.Repository;

import com.bc.revan.DataAccess.IFixtureMainNodeDal;
import com.bc.revan.DataAccess.Base.GenericRepository;
import com.bc.revan.Entities.FixtureMainNode;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;

@Repository
public class HibernateFixtureMainNodeDal extends GenericRepository<FixtureMainNode> implements IFixtureMainNodeDal {

	@Transactional
	@Override
	public List<FixtureMainNode> getBySeasonAndH2h(int year, long teamId1, long teamId2) {
		Session session = entityManager.unwrap(Session.class);
		HibernateCriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<FixtureMainNode> criteria = builder.createQuery(FixtureMainNode.class);
		Root<FixtureMainNode> fixtures = criteria.from(FixtureMainNode.class);
		criteria.select(fixtures);
		criteria.where(builder.and(builder.equal(fixtures.get("season").get("year"), year),
				builder.or(
						builder.and(builder.equal(fixtures.get("teamsMainNode").get("teamHome").get("id"), teamId1),
								builder.equal(fixtures.get("teamsMainNode").get("teamAway").get("id"), teamId2)),
						builder.and(builder.equal(fixtures.get("teamsMainNode").get("teamHome").get("id"), teamId2),
								builder.equal(fixtures.get("teamsMainNode").get("teamAway").get("id"), teamId1)))));

		TypedQuery<FixtureMainNode> query = session.createQuery(criteria);
		List<FixtureMainNode> a = query.getResultList();
		for (FixtureMainNode fixtureMainNode : a) {
			System.out.println(fixtureMainNode.getId());
		}
		return query.getResultList();
	}

	@Transactional
	@Override
	public List<FixtureMainNode> getBySeasonAndLeagueIdAndH2h(int year, long leagueId, long teamId1, long teamId2) {
		Session session = entityManager.unwrap(Session.class);
		HibernateCriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<FixtureMainNode> criteria = builder.createQuery(FixtureMainNode.class);
		Root<FixtureMainNode> fixtures = criteria.from(FixtureMainNode.class);

		criteria.select(fixtures);
		/*
		 * criteria.where(builder.equal(fixtures.get("season").get("year"), year));
		 * criteria.where(builder.equal(fixtures.get("season").get("league").get("id"),
		 * leagueId)); criteria.where(builder.equal(fixtures.get("league").get("id"),
		 * leagueId));
		 */
		criteria.where(builder.and(builder.equal(fixtures.get("season").get("year"), year),
				builder.equal(fixtures.get("league").get("id"), leagueId),
				builder.or(
						builder.and(builder.equal(fixtures.get("teamsMainNode").get("teamHome").get("id"), teamId1),
								builder.equal(fixtures.get("teamsMainNode").get("teamAway").get("id"), teamId2)),
						builder.and(builder.equal(fixtures.get("teamsMainNode").get("teamHome").get("id"), teamId2),
								builder.equal(fixtures.get("teamsMainNode").get("teamAway").get("id"), teamId1)))));

		TypedQuery<FixtureMainNode> query = session.createQuery(criteria);
		List<FixtureMainNode> a = query.getResultList();
		for (FixtureMainNode fixtureMainNode : a) {
			System.out.println(fixtureMainNode.getSeason().getId() + " " + fixtureMainNode.getSeason().getYear() + " "
					+ fixtureMainNode.getSeason().getLeague().getId());
			System.out.println(fixtureMainNode.getLeague().getId());
		}
		return query.getResultList();

	}
	
	@Transactional
	@Override
	public FixtureMainNode getByFixture(long fixtureId) {

		Session session = entityManager.unwrap(Session.class);
		HibernateCriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<FixtureMainNode> criteria = builder.createQuery(FixtureMainNode.class);
		Root<FixtureMainNode> table = criteria.from(FixtureMainNode.class);
		criteria.select(table);
		criteria.where(builder.and(builder.equal(table.get("fixture").get("id"), fixtureId), builder.equal(table.get("isDeleted"), false)));
		TypedQuery<FixtureMainNode> query = session.createQuery(criteria);
		List<FixtureMainNode> a = query.getResultList();
		if (a.size() <= 0)
			return null;
		return query.getSingleResult();
	}
	@Transactional
	@Override
	public List<FixtureMainNode> getBySeasonAndLeagueId(int year, long leagueId) {
		Session session = entityManager.unwrap(Session.class);
		HibernateCriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<FixtureMainNode> criteria = builder.createQuery(FixtureMainNode.class);
		Root<FixtureMainNode> fixtures = criteria.from(FixtureMainNode.class);

		criteria.select(fixtures);
		criteria.where(builder.and(builder.equal(fixtures.get("season").get("year"), year),
				builder.equal(fixtures.get("league").get("id"), leagueId)));
		
		TypedQuery<FixtureMainNode> query = session.createQuery(criteria);
		List<FixtureMainNode> a = query.getResultList();
		for (FixtureMainNode fixtureMainNode : a) {
			System.out.println(fixtureMainNode.getSeason().getId() + " " + fixtureMainNode.getSeason().getYear() + " "
					+ fixtureMainNode.getSeason().getLeague().getId());
			System.out.println(fixtureMainNode.getLeague().getId());
		}
		return query.getResultList();
	}
	
	@Transactional
	@Override
	public List<FixtureMainNode> getByRound(String round) {
		Session session = entityManager.unwrap(Session.class);
		HibernateCriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<FixtureMainNode> criteria = builder.createQuery(FixtureMainNode.class);
		Root<FixtureMainNode> fixtures = criteria.from(FixtureMainNode.class);

		criteria.select(fixtures);
		criteria.where(builder.equal(fixtures.get("round"),round));
		
		TypedQuery<FixtureMainNode> query = session.createQuery(criteria);
		return query.getResultList();
	}
	
	@Transactional
	@Override
	public List<FixtureMainNode> getByStatus(String statu) {
		Session session = entityManager.unwrap(Session.class);
		HibernateCriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<FixtureMainNode> criteria = builder.createQuery(FixtureMainNode.class);
		Root<FixtureMainNode> fixtures = criteria.from(FixtureMainNode.class);

		criteria.select(fixtures);
		criteria.where(builder.equal(fixtures.get("fixture").get("status").get("shortOfStatus"),statu));
		
		TypedQuery<FixtureMainNode> query = session.createQuery(criteria);
		return query.getResultList();
	}
	
	@Transactional
	@Override
	public List<FixtureMainNode> getBySeasonAndLeagueIdAndHomeTeam(int year, long leagueId, long teamId) {
		Session session = entityManager.unwrap(Session.class);
		HibernateCriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<FixtureMainNode> criteria = builder.createQuery(FixtureMainNode.class);
		Root<FixtureMainNode> fixtures = criteria.from(FixtureMainNode.class);

		criteria.select(fixtures);
		criteria.where(builder.and(builder.equal(fixtures.get("season").get("year"), year),
				builder.equal(fixtures.get("league").get("id"), leagueId),
						builder.equal(fixtures.get("teamsMainNode").get("teamHome").get("id"), teamId)));

		TypedQuery<FixtureMainNode> query = session.createQuery(criteria);
		List<FixtureMainNode> a = query.getResultList();
		for (FixtureMainNode fixtureMainNode : a) {
			System.out.println(fixtureMainNode.getSeason().getId() + " " + fixtureMainNode.getSeason().getYear() + " "
					+ fixtureMainNode.getSeason().getLeague().getId());
			System.out.println(fixtureMainNode.getLeague().getId());
		}
		return query.getResultList();

	}
	
	@Transactional
	@Override
	public List<FixtureMainNode> getBySeasonAndLeagueIdAndAwayTeam(int year, long leagueId, long teamId) {
		Session session = entityManager.unwrap(Session.class);
		HibernateCriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<FixtureMainNode> criteria = builder.createQuery(FixtureMainNode.class);
		Root<FixtureMainNode> fixtures = criteria.from(FixtureMainNode.class);

		criteria.select(fixtures);
		criteria.where(builder.and(builder.equal(fixtures.get("season").get("year"), year),
				builder.equal(fixtures.get("league").get("id"), leagueId),
						builder.equal(fixtures.get("teamsMainNode").get("teamAway").get("id"), teamId)));

		TypedQuery<FixtureMainNode> query = session.createQuery(criteria);
		List<FixtureMainNode> a = query.getResultList();
		for (FixtureMainNode fixtureMainNode : a) {
			System.out.println(fixtureMainNode.getSeason().getId() + " " + fixtureMainNode.getSeason().getYear() + " "
					+ fixtureMainNode.getSeason().getLeague().getId());
			System.out.println(fixtureMainNode.getLeague().getId());
		}
		return query.getResultList();

	}
	@Transactional
	@Override
	public List<FixtureMainNode> getBySeasonAndLeagueIdAndAwayTeamWithoutNS(int year, long leagueId, long teamId) {
		Session session = entityManager.unwrap(Session.class);
		HibernateCriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<FixtureMainNode> criteria = builder.createQuery(FixtureMainNode.class);
		Root<FixtureMainNode> fixtures = criteria.from(FixtureMainNode.class);

		criteria.select(fixtures);
		criteria.where(builder.and(builder.equal(fixtures.get("season").get("year"), year),
				builder.equal(fixtures.get("league").get("id"), leagueId),
						builder.equal(fixtures.get("teamsMainNode").get("teamAway").get("id"), teamId),
						builder.notEqual(fixtures.get("fixture").get("status").get("shortOfStatus"),"NS")));

		TypedQuery<FixtureMainNode> query = session.createQuery(criteria);
		List<FixtureMainNode> a = query.getResultList();
		for (FixtureMainNode fixtureMainNode : a) {
			System.out.println(fixtureMainNode.getSeason().getId() + " " + fixtureMainNode.getSeason().getYear() + " "
					+ fixtureMainNode.getSeason().getLeague().getId());
			System.out.println(fixtureMainNode.getLeague().getId());
		}
		return query.getResultList();

	}
	@Transactional
	@Override
	public List<FixtureMainNode> getBySeasonAndLeagueIdAndHomeTeamWithoutNS(int year, long leagueId, long teamId) {
		Session session = entityManager.unwrap(Session.class);
		HibernateCriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<FixtureMainNode> criteria = builder.createQuery(FixtureMainNode.class);
		Root<FixtureMainNode> fixtures = criteria.from(FixtureMainNode.class);

		criteria.select(fixtures);
		criteria.where(builder.and(builder.equal(fixtures.get("season").get("year"), year),
				builder.equal(fixtures.get("league").get("id"), leagueId),
						builder.equal(fixtures.get("teamsMainNode").get("teamHome").get("id"), teamId),
						builder.notEqual(fixtures.get("fixture").get("status").get("shortOfStatus"),"NS")));

		TypedQuery<FixtureMainNode> query = session.createQuery(criteria);
		List<FixtureMainNode> a = query.getResultList();
		for (FixtureMainNode fixtureMainNode : a) {
			System.out.println(fixtureMainNode.getSeason().getId() + " " + fixtureMainNode.getSeason().getYear() + " "
					+ fixtureMainNode.getSeason().getLeague().getId());
			System.out.println(fixtureMainNode.getLeague().getId());
		}
		return query.getResultList();
	}


}
