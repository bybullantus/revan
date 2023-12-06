package com.bc.revan.Business;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.bc.revan.Business.Base.IBaseService;
import com.bc.revan.Entities.Country;
import com.bc.revan.Entities.StatisticForTeam;


public interface ICountryService extends IBaseService<Country> {
	CompletableFuture<List<Country>> getByCode(String code);
	CompletableFuture<Country> getByName(String name);
}
