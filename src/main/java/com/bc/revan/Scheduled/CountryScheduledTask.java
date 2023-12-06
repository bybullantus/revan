package com.bc.revan.Scheduled;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bc.revan.Entegration.ICountryScheduledService;

@Component
public class CountryScheduledTask {

	/*Scheduled Çalışma sırası
	 * contry
	 * league
	 * team
	 * squadForPlayers
	 * statisticforteam 
	 * fixture 
	 * statisticsforfixture
	 * eventsforfixture
	 * standing
	 * injuries
	 * coachs
	 * lineupforfixture
	 * playersforfixture
	 * */
	
	
	@Autowired
	 ICountryScheduledService countryScheduledService;

	 static final SimpleDateFormat dateformat=new SimpleDateFormat("HH:mm:ss");
	
	//@Scheduled(fixedRate = 1000*60*60*24)
	public void getCountryTask() {
		
		countryScheduledService.getCountries();
		
	}
	
	
	
	
}
