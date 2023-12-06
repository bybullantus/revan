package com.bc.revan.DataAccess.Hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bc.revan.DataAccess.IFakeMatchesDal;
import com.bc.revan.Entities.FakeMatches;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

@Repository
public class HibernateFakeMatchesDal implements IFakeMatchesDal {

	@Autowired
	protected EntityManager entityManager;
	
	@Transactional
	@Override
	public List<FakeMatches> getAll() {
		Session session = entityManager.unwrap(Session.class);
		HibernateCriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<FakeMatches> criteria = builder.createQuery(FakeMatches.class);
		Root<FakeMatches> table = criteria.from(FakeMatches.class);
		criteria.where(builder.equal(table.get("isDeleted"), false));
		criteria.select(table).orderBy(builder.asc(table.get("id")));
		TypedQuery<FakeMatches> query = session.createQuery(criteria);
		return query.getResultList();
	}

	@Transactional
	@Override
	public FakeMatches add(FakeMatches item) {
		Session session = entityManager.unwrap(Session.class);
		try {

			session.saveOrUpdate(item);
		} catch (Exception e) {
			session.merge(item);
		}
		// entityManager.unwrap(Session.class).saveOrUpdate(item); return item;
		return item;
	}
	@Transactional
	@Override
	public List<FakeMatches> addAll(List<FakeMatches> items) {
		Session session = entityManager.unwrap(Session.class);
		for (FakeMatches t : items) {
			session.merge(t);
		}
		return items;
	}
	@Transactional
	@Override
	public FakeMatches update(FakeMatches item) {
		Session session = entityManager.unwrap(Session.class);
		session.merge(item);
		return item;
	}
	@Transactional
	@Override
	public void delete(FakeMatches item) {
		Session session = entityManager.unwrap(Session.class);
		session.delete(item);
		
		// entityManager.unwrap(Session.class).saveOrUpdate(item);

	}
	@Transactional
	@Override
	public FakeMatches getById(long id) {
		Session session = entityManager.unwrap(Session.class);
		HibernateCriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<FakeMatches> criteria = builder.createQuery(FakeMatches.class);
		Root<FakeMatches> table = criteria.from(FakeMatches.class);
		criteria.select(table);
		criteria.where(builder.and(builder.equal(table.get("id"), id)));
		TypedQuery<FakeMatches> query = session.createQuery(criteria);
		List<FakeMatches> a = query.getResultList();
		if (a.size() <= 0)
			return null;
		return query.getSingleResult();
	}
	@Transactional
	@Override
	public List<FakeMatches> getByHafta(int hafta) {
		Session session = entityManager.unwrap(Session.class);
		HibernateCriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<FakeMatches> criteria = builder.createQuery(FakeMatches.class);
		Root<FakeMatches> table = criteria.from(FakeMatches.class);
		criteria.select(table);
		criteria.where(builder.and(builder.equal(table.get("hafta"), hafta)));
		TypedQuery<FakeMatches> query = session.createQuery(criteria);
		List<FakeMatches> a = query.getResultList();
		if (a.size() <= 0)
			return null;
		return query.getResultList();
	}
}
