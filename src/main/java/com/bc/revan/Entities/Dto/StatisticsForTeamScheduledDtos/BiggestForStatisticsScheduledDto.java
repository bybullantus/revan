package com.bc.revan.Entities.Dto.StatisticsForTeamScheduledDtos;

import java.io.Serializable;


import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public class BiggestForStatisticsScheduledDto implements Serializable{

	Streak streak;
	Wins wins;
	Loses loses;
	Goals goals;

	@Data
	public static class Streak{		
		int wins;
		int draws;
		int loses;
	}
	@Data
	public static class Wins{		
		String home;
		String away;

	}
	@Data
	public static class Loses{		
		String home;
		String away;

	}
	@Data
	public static class Goals{		
		@JsonProperty("for")
		For fors;
		
		Against against;

	}
	@Data
	public static class For{		
		String home;
		String away;

	}
	@Data
	public static class Against{		
		String home;
		String away;

	}
	
}
