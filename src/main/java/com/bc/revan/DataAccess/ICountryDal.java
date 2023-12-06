package com.bc.revan.DataAccess;

import java.util.List;

import com.bc.revan.DataAccess.Base.IBaseRepository;
import com.bc.revan.Entities.Country;

public interface ICountryDal extends IBaseRepository<Country> {
	
	List<Country> getByCode(String code);
	Country getByName(String name);
}
