package com.bc.revan.DataAccess.Base;

import java.util.HashSet;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.bc.revan.Entities.Base.BaseEntity;

public interface IBaseRepository<T extends BaseEntity> {
	List<T> getAll();

	T add(T item);
	List<T> addAll(List<T> items);

	T update(T item);

	void delete(T item);

	T getById(long id);
	
	
}
