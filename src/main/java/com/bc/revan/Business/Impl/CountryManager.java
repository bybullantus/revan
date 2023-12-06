package com.bc.revan.Business.Impl;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.bc.revan.Business.ICountryService;
import com.bc.revan.Business.Base.GenericManager;
import com.bc.revan.DataAccess.ICountryDal;
import com.bc.revan.Entities.Country;

import jakarta.transaction.Transactional;

@Service
public class CountryManager extends GenericManager<Country,ICountryDal> implements ICountryService {

	
	@Override
	@Async
	public CompletableFuture<List<Country>> getByCode(String code) {
		return CompletableFuture.completedFuture(genericDal.getByCode(code));
	}

	
	@Override
	@Async
	public CompletableFuture<Country> getByName(String name) {
		 
		return CompletableFuture.completedFuture(genericDal.getByName(name));
	}

}
