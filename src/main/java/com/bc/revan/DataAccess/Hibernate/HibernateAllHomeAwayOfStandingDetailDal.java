package com.bc.revan.DataAccess.Hibernate;

import org.hibernate.Session;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;
import org.springframework.stereotype.Repository;

import com.bc.revan.DataAccess.IAllHomeAwayOfStandingDetailDal;
import com.bc.revan.DataAccess.Base.GenericRepository;
import com.bc.revan.Entities.AllHomeAwayOfStandingDetail;
import com.bc.revan.Entities.Standing;

import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;

@Repository
public class HibernateAllHomeAwayOfStandingDetailDal extends GenericRepository<AllHomeAwayOfStandingDetail>
		implements IAllHomeAwayOfStandingDetailDal {

	@Transactional
	@Override
	public void deleteAllHomeAwayOfStandingDetail() {
		try {
            Session session = entityManager.unwrap(Session.class);
            
            session.createNativeQuery("SET session_replication_role = replica").executeUpdate();
            
            int deletedCount = session.createQuery("DELETE FROM AllHomeAwayOfStandingDetail").executeUpdate();
            
            session.createNativeQuery("SET session_replication_role = DEFAULT").executeUpdate();
            
            System.out.println("Deleted " + deletedCount + " records");
        } catch (Exception e) {
            e.printStackTrace();
        }
		
		
	}

}
