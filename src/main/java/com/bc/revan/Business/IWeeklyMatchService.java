package com.bc.revan.Business;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.bc.revan.Business.Base.IBaseService;
import com.bc.revan.Entities.WeeklyMatch;

public interface IWeeklyMatchService extends IBaseService<WeeklyMatch>{
	CompletableFuture<List<String>>  groupByRound();
	CompletableFuture<List<Integer>>  groupByYear();
	CompletableFuture<List<Long>>  groupByWeekNumber();
	CompletableFuture<List<WeeklyMatch>>  getBySeasonAndWeek(int year,long week);

}
