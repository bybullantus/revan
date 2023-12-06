package com.bc.revan.DataAccess.Hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bc.revan.DataAccess.ICoachsDal;
import com.bc.revan.DataAccess.Base.GenericRepository;
import com.bc.revan.Entities.Coachs;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

@Repository
public class HibernateCoachsDal  extends GenericRepository<Coachs> implements ICoachsDal {


	@Transactional
	@Override
	public List<Coachs> getByTeam(long teamId) {
		Session session = entityManager.unwrap(Session.class);
		HibernateCriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Coachs> criteria = builder.createQuery(Coachs.class);
		Root<Coachs> coachs = criteria.from(Coachs.class);
		criteria.select(coachs);
		criteria.where(builder.equal(coachs.get("team").get("id"), teamId));
		TypedQuery<Coachs> query = session.createQuery(criteria);
		return query.getResultList();
	}

}
