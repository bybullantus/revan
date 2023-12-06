package com.bc.revan.Entities.Dto.StatisticsForTeamScheduledDtos;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public class GoalsForStatisticsScheduledDto implements Serializable{

	@JsonProperty("for")
	For forOfGoals;
	
	Against against;


	@Data
	public static class For{		
		Total total;
		Average average;	
		Minutes minute;
	}
	@Data
	public static class Against{		
		Total total;
		Average average;
		Minutes minute;
	}
	
	@Data
	public static class Total{		
		BigDecimal home;
		BigDecimal away;
		BigDecimal total;
	}
	@Data
	public static class Average{		
		String home;
		String away;
		String total;
	}
	@Data
	public static class Minutes{		
		
		@JsonProperty("0-15")
		ZeroToFifteen zeroToFifteen;
		@JsonProperty("16-30")
		SixteenToThirty sixteenToThirty;
		@JsonProperty("31-45")
		ThirtyOneToFortyFive thirtyOneToFortyFive;
		@JsonProperty("46-60")
		FortySixToSixty fortySixToSixty;
		@JsonProperty("61-75")
		SixtyOneToSeventyFive sixtyOneToSeventyFive;
		@JsonProperty("76-90")
		SeventySixToNinety seventySixToNinety;
		@JsonProperty("91-105")
		NinetyOneToOneHundredFive ninetyOneToOneHundredFive;
		@JsonProperty("106-120")
		OneHundredSixToOneHundredTwenty oneHundredSixToOneHundredTwenty;
		
	}
	@Data
	public static class ZeroToFifteen{		
		int total;
		String percentage;
	}
	@Data
	public static class SixteenToThirty{		
		int total;
		String percentage;
	}
	@Data
	public static class ThirtyOneToFortyFive{		
		int total;
		String percentage;
	}
	@Data
	public static class FortySixToSixty{		
		int total;
		String percentage;
	}
	@Data
	public static class SixtyOneToSeventyFive{		
		int total;
		String percentage;
	}
	@Data
	public static class SeventySixToNinety{		
		int total;
		String percentage;
	}
	@Data
	public static class NinetyOneToOneHundredFive{		
		int total;
		String percentage;
	}
	@Data
	public static class OneHundredSixToOneHundredTwenty{		
		int total;
		String percentage;
	}
	
}
