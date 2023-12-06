package com.bc.revan.Business.Impl;

import org.springframework.stereotype.Service;

import com.bc.revan.Business.IFixtureService;
import com.bc.revan.Business.Base.GenericManager;
import com.bc.revan.DataAccess.IFixtureDal;
import com.bc.revan.Entities.Fixture;


@Service
public class FixtureManager extends GenericManager<Fixture,IFixtureDal> implements IFixtureService {

	

}
