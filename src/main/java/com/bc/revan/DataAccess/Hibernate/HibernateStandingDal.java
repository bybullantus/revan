package com.bc.revan.DataAccess.Hibernate;

import org.hibernate.Session;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bc.revan.DataAccess.IStandingDal;
import com.bc.revan.DataAccess.Base.GenericRepository;
import com.bc.revan.Entities.Standing;

import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.Root;

@Repository
public class HibernateStandingDal extends GenericRepository<Standing> implements IStandingDal {

	@Transactional
	@Override
	public void deleteAllStanding() {
		try {
            Session session = entityManager.unwrap(Session.class);
            
            session.createNativeQuery("SET session_replication_role = replica").executeUpdate();
            
            int deletedCount = session.createQuery("DELETE FROM Standing").executeUpdate();
            
            session.createNativeQuery("SET session_replication_role = DEFAULT").executeUpdate();
            
            System.out.println("Deleted " + deletedCount + " records");
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
}
