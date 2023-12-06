package com.bc.revan.DataAccess.Hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bc.revan.DataAccess.IStatisticForTeamDal;
import com.bc.revan.DataAccess.Base.GenericRepository;
import com.bc.revan.Entities.StatisticForTeam;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
@Repository
public class HibernateStatisticForTeamDal extends GenericRepository<StatisticForTeam> implements IStatisticForTeamDal {

	@Transactional
	@Override
	public StatisticForTeam getBySeasonAndLeagueIdAndTeamId(int year, long leagueId, long teamId) {
		Session session = entityManager.unwrap(Session.class);
		HibernateCriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<StatisticForTeam> criteria = builder.createQuery(StatisticForTeam.class);
		Root<StatisticForTeam> statistics = criteria.from(StatisticForTeam.class);
		criteria.select(statistics);
		criteria.where(builder.equal(statistics.get("season").get("year"), year));
		criteria.where(builder.equal(statistics.get("season").get("league").get("id"), leagueId));
		criteria.where(builder.equal(statistics.get("league").get("id"), leagueId));
		criteria.where(builder.equal(statistics.get("team").get("id"), teamId));

		TypedQuery<StatisticForTeam> query = session.createQuery(criteria);
		List<StatisticForTeam> a = query.getResultList();
		if (a.size() <= 0)
			return null;
		return query.getSingleResult();
	}

}
