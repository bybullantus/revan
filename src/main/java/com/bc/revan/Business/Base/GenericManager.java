package com.bc.revan.Business.Base;



import java.util.HashSet;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.bc.revan.DataAccess.Base.IBaseRepository;
import com.bc.revan.Entities.Base.BaseEntity;

import jakarta.persistence.MappedSuperclass;
import jakarta.transaction.Transactional;

@MappedSuperclass
public abstract class GenericManager<T extends BaseEntity,K extends IBaseRepository<T> > implements IBaseService<T> {
	
	
	@Autowired
	protected K genericDal;
	
	
	@Override
	@Async
	public CompletableFuture<List<T>> getAll() {
		return CompletableFuture.completedFuture( genericDal.getAll());
	}
	
	@Override
	@Async
	public CompletableFuture<T> add(T item) {
		return CompletableFuture.completedFuture( genericDal.add(item));
	}
	@Override
	public List<T> addAll(List<T> items) {
		return genericDal.addAll(items);
	}
	
	@Override
	@Async
	public CompletableFuture<T> update(T item) {
		return CompletableFuture.completedFuture( genericDal.update(item));
	}

	
	@Override
	public void delete(T item) {
		genericDal.delete(item);
	}

	
	@Override
	@Async
	public CompletableFuture<T> getById(Long id) {
		return CompletableFuture.completedFuture( genericDal.getById(id));
	}

	
	    
}
