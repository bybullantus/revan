package com.bc.revan.DataAccess.Hibernate;

import org.springframework.stereotype.Repository;

import com.bc.revan.DataAccess.ICleanSheetOfStatisticsDal;
import com.bc.revan.DataAccess.Base.GenericRepository;
import com.bc.revan.Entities.CleanSheetOfStatistics;

@Repository
public class HibernateCleanSheetOfStatisticsDal extends GenericRepository<CleanSheetOfStatistics>
		implements ICleanSheetOfStatisticsDal {

}
