package com.bc.revan.DataAccess.Hibernate;

import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;
import org.springframework.stereotype.Repository;

import com.bc.revan.DataAccess.ITokenDal;
import com.bc.revan.DataAccess.Base.GenericRepository;
import com.bc.revan.Entities.Coachs;
import com.bc.revan.Entities.Token;
import com.bc.revan.Entities.User;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
@Repository
public class HibernateTokenDal   extends GenericRepository<Token> implements ITokenDal {

	
	@Override
	@Transactional
	public List<Token> getTokenbyUserId(Long id) {
		Session session = entityManager.unwrap(Session.class);
		HibernateCriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Token> criteria = builder.createQuery(Token.class);
		Root<Token> table = criteria.from(Token.class);
		criteria.where(builder.equal(table.get("id"), id));
		criteria.select(table).orderBy(builder.asc(table.get("id")));
		TypedQuery<Token> query = session.createQuery(criteria);
		return query.getResultList();
	}

	@Override
	@Transactional
	public Optional<Token> getByToken(String token) {

		Session session = entityManager.unwrap(Session.class);
		HibernateCriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Token> criteria = builder.createQuery(Token.class);
		Root<Token> table = criteria.from(Token.class);
		criteria.select(table);
		criteria.where(builder.equal(table.get("token"), token));
		TypedQuery<Token> query = session.createQuery(criteria);
		return Optional.of(query.getSingleResult());
	}

	@Override
	@Transactional
	public boolean getByEmail(String email) {
		Session session = entityManager.unwrap(Session.class);
		HibernateCriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<User> criteria = builder.createQuery(User.class);
		Root<User> table = criteria.from(User.class);
		criteria.select(table);
		criteria.where(builder.equal(table.get("email"), email));
		TypedQuery<User> query = session.createQuery(criteria);
		if(query.getSingleResult()==null)
			return false;
		
		return true;
	}

}
