package com.bc.revan.Entegration;

import java.util.List;
import java.util.concurrent.ExecutionException;

import com.bc.revan.Entities.WeeklyMatch;

public interface IWeeklyMatchesScheduledService {
	List<WeeklyMatch> getWeeklyMatches() throws InterruptedException, ExecutionException;
}
