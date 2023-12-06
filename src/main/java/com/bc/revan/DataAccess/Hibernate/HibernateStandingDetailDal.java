package com.bc.revan.DataAccess.Hibernate;

import org.hibernate.Session;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;
import org.springframework.stereotype.Repository;

import com.bc.revan.DataAccess.IStandingDetailDal;
import com.bc.revan.DataAccess.Base.GenericRepository;
import com.bc.revan.Entities.StandingDetail;

import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;

@Repository
public class HibernateStandingDetailDal extends GenericRepository<StandingDetail> implements IStandingDetailDal {

	@Transactional
	@Override
	public void deleteAllStandingDetail() {
		try {
            Session session = entityManager.unwrap(Session.class);
            
            session.createNativeQuery("SET session_replication_role = replica").executeUpdate();
            
            int deletedCount = session.createQuery("DELETE FROM StandingDetail").executeUpdate();
            
            session.createNativeQuery("SET session_replication_role = DEFAULT").executeUpdate();
            
            System.out.println("Deleted " + deletedCount + " records");
        } catch (Exception e) {
            e.printStackTrace();
        }
		
	}

}
