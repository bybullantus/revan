package com.bc.revan.DataAccess.Hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bc.revan.DataAccess.IStandingMainNodeDal;
import com.bc.revan.DataAccess.Base.GenericRepository;
import com.bc.revan.Entities.StandingMainNode;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

@Repository
public class HibernateStandingMainNodeDal extends GenericRepository<StandingMainNode> implements IStandingMainNodeDal {

	@Transactional
	@Override
	public StandingMainNode getBySeasonAndLeagueId(int year, long leagueId) {
		Session session = entityManager.unwrap(Session.class);
		HibernateCriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<StandingMainNode> criteria = builder.createQuery(StandingMainNode.class);
		Root<StandingMainNode> standings = criteria.from(StandingMainNode.class);
		criteria.select(standings);
		criteria.where(builder.equal(standings.get("season").get("year"), year));
		criteria.where(builder.equal(standings.get("season").get("league").get("id"), leagueId));
		criteria.where(builder.equal(standings.get("league").get("id"), leagueId));

		TypedQuery<StandingMainNode> query = session.createQuery(criteria);
		List<StandingMainNode> a = query.getResultList();
		if (a.size() <= 0)
			return null;
		return query.getSingleResult();
	}
	
	@Transactional
	@Override
	public void deleteAllStandingMain() {
		 /*Session session = entityManager.unwrap(Session.class);
		    HibernateCriteriaBuilder builder = session.getCriteriaBuilder();
		    CriteriaDelete<StandingMainNode> deleteCriteria = builder.createCriteriaDelete(StandingMainNode.class);
		    Root<StandingMainNode> standings = deleteCriteria.from(StandingMainNode.class);
		   

		    int deletedCount = session.createQuery(deleteCriteria).executeUpdate();
		    
		    System.out.println("Deleted " + deletedCount + " records");*/
		    
		try {
            Session session = entityManager.unwrap(Session.class);
            
            session.createNativeQuery("SET session_replication_role = replica").executeUpdate();
            
            int deletedCount = session.createQuery("DELETE FROM StandingMainNode").executeUpdate();
            
            session.createNativeQuery("SET session_replication_role = DEFAULT").executeUpdate();
            
            System.out.println("Deleted " + deletedCount + " records");
        } catch (Exception e) {
            e.printStackTrace();
        }
		
	}

}
