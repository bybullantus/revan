package com.bc.revan.Business.Base;

import java.util.HashSet;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface IBaseService<T> {
	
	CompletableFuture<List<T>> getAll();

	CompletableFuture<T> add(T item);
	
	List<T> addAll(List<T> items);

	CompletableFuture<T> update(T item);

	void delete(T item);

	CompletableFuture<T> getById(Long id);
}
