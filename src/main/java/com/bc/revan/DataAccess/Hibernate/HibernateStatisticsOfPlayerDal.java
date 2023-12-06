package com.bc.revan.DataAccess.Hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bc.revan.DataAccess.IStatisticsOfPlayerDal;
import com.bc.revan.DataAccess.Base.GenericRepository;
import com.bc.revan.Entities.StatisticsOfPlayer;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

@Repository
public class HibernateStatisticsOfPlayerDal extends GenericRepository<StatisticsOfPlayer>
		implements IStatisticsOfPlayerDal {

	@Transactional
	@Override
	public List<StatisticsOfPlayer> getBySeasonAndPlayerId(int year, long playerId) {
		Session session = entityManager.unwrap(Session.class);
		HibernateCriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<StatisticsOfPlayer> criteria = builder.createQuery(StatisticsOfPlayer.class);
		Root<StatisticsOfPlayer> statistics = criteria.from(StatisticsOfPlayer.class);
		criteria.select(statistics);
		criteria.where(builder.and
				(builder.equal(statistics.get("season").get("year"), year),
				(builder.equal(statistics.get("player").get("id"), playerId)
						)));
		TypedQuery<StatisticsOfPlayer> query = session.createQuery(criteria);
		List<StatisticsOfPlayer> a = query.getResultList();
		if (a.size() <= 0)
			return null;
		return query.getResultList();
	}

	@Transactional
	@Override
	public List<StatisticsOfPlayer> getBySeasonAndPlayerIdAndLeagueId(int year, long playerId, long leagueId) {
		Session session = entityManager.unwrap(Session.class);
		HibernateCriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<StatisticsOfPlayer> criteria = builder.createQuery(StatisticsOfPlayer.class);
		Root<StatisticsOfPlayer> statistics = criteria.from(StatisticsOfPlayer.class);
		criteria.select(statistics);
		criteria.where(builder.and
				(builder.equal(statistics.get("season").get("year"), year),
				(builder.equal(statistics.get("player").get("id"), playerId)
						)));
		criteria.where(builder.equal(statistics.get("season").get("league").get("id"), leagueId));
		TypedQuery<StatisticsOfPlayer> query = session.createQuery(criteria);
		List<StatisticsOfPlayer> a = query.getResultList();
		if (a.size() <= 0)
			return null;
		return query.getResultList();
	}

}
