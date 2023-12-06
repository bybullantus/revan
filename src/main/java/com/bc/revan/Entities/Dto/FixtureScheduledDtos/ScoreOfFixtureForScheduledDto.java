package com.bc.revan.Entities.Dto.FixtureScheduledDtos;

import java.io.Serializable;

import com.bc.revan.Entegration.Football.FixtureScheduledService;
import com.bc.revan.Entities.Dto.FixtureScheduledDtos.TeamsOfFixtureForScheduledDto.Away;
import com.bc.revan.Entities.Dto.FixtureScheduledDtos.TeamsOfFixtureForScheduledDto.Home;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public class ScoreOfFixtureForScheduledDto implements Serializable{

	HalfTime halftime;
	FullTime fulltime;
	ExtraTime extratime;
	Penalty penalty;
	
	@Data
	public static class HalfTime{		
	
		 int home;
		 int away;
	}
	@Data
	public static class FullTime{		
	
		 int home;
		 int away;
	}
	@Data
	public static class ExtraTime{		
	
		 int home;
		 int away;
	}
	@Data
	public static class Penalty{		
	
		 int home;
		 int away;
	}


}
