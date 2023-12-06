package com.bc.revan.Entities.Dto.FixtureScheduledDtos;

import java.io.Serializable;

import com.bc.revan.Entities.Dto.FixtureScheduledDtos.FixtureForScheduledDto.Periods;
import com.bc.revan.Entities.Dto.FixtureScheduledDtos.FixtureForScheduledDto.Venue;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public class TeamsOfFixtureForScheduledDto implements Serializable{

	Home home;
	Away away;
	
	
	@Data
	public static class Home{		
		long id;
		String name;
		String logo;
		boolean winner;
		
	}

	@Data
	public static class Away{		
		long id;
		String name;
		String logo;
		boolean winner;
		
	}
}
