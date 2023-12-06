package com.bc.revan.DataAccess.Hibernate;

import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;
import org.springframework.stereotype.Repository;

import com.bc.revan.DataAccess.IUserDal;
import com.bc.revan.DataAccess.Base.GenericRepository;
import com.bc.revan.Entities.CardsOfStatistics;
import com.bc.revan.Entities.User;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;

@Repository
public class HibernateUserDal extends GenericRepository<User> implements IUserDal {
	
	@Transactional
	@Override
	public Optional<User> getByEmail(String email) {

		Session session = entityManager.unwrap(Session.class);
		HibernateCriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<User> criteria = builder.createQuery(User.class);
		Root<User> table = criteria.from(User.class);
		criteria.select(table);
		criteria.where(
				builder.and(builder.equal(table.get("email"), email), builder.equal(table.get("isDeleted"), false)));
		TypedQuery<User> query = session.createQuery(criteria);
		List<User> list = query.getResultList();
		if (list == null || list.isEmpty())
			return Optional.empty();
		return Optional.of(list.get(0));

	}

}
