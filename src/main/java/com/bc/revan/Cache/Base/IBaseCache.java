package com.bc.revan.Cache.Base;

import java.util.Map;

import com.bc.revan.Entities.Base.BaseEntity;

public interface IBaseCache<T > {

	Map<Object, Object> getAll();

	void add(T item);

	void delete(String id);
	
	void deleteAll();

	T getById(String id);
}