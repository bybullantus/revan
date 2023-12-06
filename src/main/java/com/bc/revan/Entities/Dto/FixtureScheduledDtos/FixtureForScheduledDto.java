package com.bc.revan.Entities.Dto.FixtureScheduledDtos;

import java.io.Serializable;

import com.bc.revan.Entities.Dto.StandingScheduledDtos.StandingForStandingsScheduledDto;
import com.bc.revan.Entities.Dto.StandingScheduledDtos.StandingForStandingsScheduledDto.All;
import com.bc.revan.Entities.Dto.StandingScheduledDtos.StandingForStandingsScheduledDto.Away;
import com.bc.revan.Entities.Dto.StandingScheduledDtos.StandingForStandingsScheduledDto.Home;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public class FixtureForScheduledDto implements Serializable{
	long id;
	String referee;
	String timezone;
	String date;
	long timestamp;
	Periods periods;
	Venue venue;
	Status status;
	
	@Data
	public static class Periods{		
	
		long first;		
		long second;
	}
	@Data
	public static class Venue{		
	
		long id;		
		String name;
		String city;
	}
	@Data
	public static class Status{		
	
		@JsonProperty("short")
		String shortOfStatus;
		@JsonProperty("long")
		String longOfStatus;
		int elapsed;	
	}
	
}
