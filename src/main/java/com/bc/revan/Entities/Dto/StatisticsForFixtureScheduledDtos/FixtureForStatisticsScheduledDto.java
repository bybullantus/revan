package com.bc.revan.Entities.Dto.StatisticsForFixtureScheduledDtos;

import java.io.Serializable;

import com.bc.revan.Entegration.Football.StatisticForTeamScheduledService;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public class FixtureForStatisticsScheduledDto implements Serializable{

	
	Played played;	
	Wins wins;
	Draws draws;
	Loses loses;
	
	@Data
	public class Played{		
		int home;
		int away;
		int total;
	}
	@Data
	public class Wins{		
		int home;
		int away;
		int total;
	}
	@Data
	public class Loses{		
		int home;
		int away;
		int total;
	}
	@Data
	public class Draws{		
		int home;
		int away;
		int total;
	}
	
}
