package com.bc.revan.DataAccess.Hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bc.revan.DataAccess.ISquadDal;
import com.bc.revan.DataAccess.Base.GenericRepository;
import com.bc.revan.Entities.Squad;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

@Repository
public class HibernateSquadDal extends GenericRepository<Squad> implements ISquadDal {

	@Transactional
	@Override
	public Squad getByTeam(long teamId) {
		Session session = entityManager.unwrap(Session.class);
		HibernateCriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Squad> criteria = builder.createQuery(Squad.class);
		Root<Squad> table = criteria.from(Squad.class);
		criteria.select(table);
		criteria.where(builder.equal(table.get("team").get("id"), teamId));
		TypedQuery<Squad> query = session.createQuery(criteria);
		List<Squad> a = query.getResultList();
		if (a.size() <= 0)
			return null;
		return query.getSingleResult();
	}

}
