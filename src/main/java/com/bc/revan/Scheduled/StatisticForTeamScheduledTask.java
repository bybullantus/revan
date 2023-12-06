package com.bc.revan.Scheduled;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.bc.revan.Entegration.IStatisticForTeamScheduledService;

@Component
public class StatisticForTeamScheduledTask {
	@Autowired
	IStatisticForTeamScheduledService statisticForTeamScheduledService;

	 static final SimpleDateFormat dateformat=new SimpleDateFormat("HH:mm:ss");
	
	//@Scheduled(fixedRate = 1000*60*60*24)
	public void getStatisticsForTeamTask() {
		
		statisticForTeamScheduledService.getStatisticsForTeam();
		
	}
}
