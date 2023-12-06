package com.bc.revan.Scheduled;

import java.text.SimpleDateFormat;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.bc.revan.Entegration.IPlayersForFixtureScheduledService;

@Component
public class PlayersForFixtureScheduledTask {
	@Autowired
	IPlayersForFixtureScheduledService playersForFixtureScheduledService;
	
	static final SimpleDateFormat dateformat = new SimpleDateFormat("HH:mm:ss");
	
		//@Scheduled(fixedRate = 1000 * 60 * 60 * 24)
		public void getEventsForFixtureTask() throws InterruptedException,ExecutionException {
			
			playersForFixtureScheduledService.getPlayers();
			
		}
}
