package com.bc.revan.Scheduled;

import java.text.SimpleDateFormat;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.bc.revan.Entegration.IFixtureScheduledService;

@Component
public class FixtureScheduledTask {
	@Autowired
	IFixtureScheduledService fixtureScheduledService;

	static final SimpleDateFormat dateformat = new SimpleDateFormat("HH:mm:ss");

    //@Scheduled(fixedRate = 1000*60*60*24)
	public void getFixtureTask() throws InterruptedException, ExecutionException {

		fixtureScheduledService.getFixture();

	}
}
