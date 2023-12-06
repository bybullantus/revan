package com.bc.revan.Scheduled;

import java.text.SimpleDateFormat;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.bc.revan.Entegration.ILiveMatchesScheduledService;

@Component
public class LiveMatchesScheduledTask {
	
	@Autowired
	ILiveMatchesScheduledService liveMatchesScheduledService;
	static final SimpleDateFormat dateformat = new SimpleDateFormat("HH:mm:ss");
	/*@Value("TestScheduled")
	String testValue;*/
	
	@Scheduled(fixedRate = 1000 * 120 )
	public void getLiveMatchesScheduledTask() throws InterruptedException,ExecutionException {
	/*	if (!testValue.equals("TAMAM")) {
			return;
		}*/
		liveMatchesScheduledService.getLiveMatches();
		
	}
}
