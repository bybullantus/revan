package com.bc.revan.DataAccess.Hibernate;

import org.springframework.stereotype.Repository;

import com.bc.revan.DataAccess.ITotalAndAverageDal;
import com.bc.revan.DataAccess.Base.GenericRepository;
import com.bc.revan.Entities.TotalAndAverage;
@Repository
public class HibernateTotalAndAverageDal extends GenericRepository<TotalAndAverage> implements ITotalAndAverageDal {

}
