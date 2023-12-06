package com.bc.revan.DataAccess.Hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bc.revan.DataAccess.ICountryDal;
import com.bc.revan.DataAccess.Base.GenericRepository;
import com.bc.revan.Entities.Country;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

@Repository
public class HibernateCountryDal extends GenericRepository<Country> implements ICountryDal {
	
	@Transactional
	@Override
	public List<Country> getByCode(String code) {
		Session session = entityManager.unwrap(Session.class);
		HibernateCriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Country> criteria = builder.createQuery(Country.class);
		Root<Country> countries = criteria.from(Country.class);
		criteria.select(countries).where(builder.equal(countries.get("code"), code));
		TypedQuery<Country> query = session.createQuery(criteria);
		return query.getResultList();
	}

	@Transactional
	@Override
	public Country getByName(String name) {
		Session session = entityManager.unwrap(Session.class);
		HibernateCriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Country> criteria = builder.createQuery(Country.class);
		Root<Country> countries = criteria.from(Country.class);

		criteria.select(countries);
		criteria.where(builder.equal(countries.get("name"), name));
		TypedQuery<Country> query = session.createQuery(criteria);
		List<Country> a = query.getResultList();
		if (a.size() <= 0)
			return null;
		return query.getSingleResult();
	}

}
