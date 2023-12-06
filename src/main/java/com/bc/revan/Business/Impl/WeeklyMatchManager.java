package com.bc.revan.Business.Impl;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.bc.revan.Business.IWeeklyMatchService;
import com.bc.revan.Business.Base.GenericManager;
import com.bc.revan.DataAccess.IWeeklyMatchDal;
import com.bc.revan.Entities.WeeklyMatch;

import jakarta.transaction.Transactional;

@Service
public class WeeklyMatchManager extends GenericManager<WeeklyMatch,IWeeklyMatchDal> implements IWeeklyMatchService {

	@Async
	@Transactional
	@Override
	public CompletableFuture<List<String>> groupByRound() {
		
		return CompletableFuture.completedFuture(genericDal.groupByRound());
	}

	@Async
	@Transactional
	@Override
	public CompletableFuture<List<Integer>> groupByYear() {
		return CompletableFuture.completedFuture(genericDal.groupByYear());

	}
	@Async
	@Transactional
	@Override
	public CompletableFuture<List<Long>> groupByWeekNumber() {
		return CompletableFuture.completedFuture(genericDal.groupByWeekNumber());

	}

	@Async
	@Transactional
	@Override
	public CompletableFuture<List<WeeklyMatch>> getBySeasonAndWeek(int year, long week) {
		return CompletableFuture.completedFuture(genericDal.getBySeasonAndWeek(year, week));
	}


}
