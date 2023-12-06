package com.bc.revan.Entities.Dto.StatisticsForTeamScheduledDtos;


import com.bc.revan.Entities.Dto.StatisticsForTeamScheduledDtos.GoalsForStatisticsScheduledDto.FortySixToSixty;
import com.bc.revan.Entities.Dto.StatisticsForTeamScheduledDtos.GoalsForStatisticsScheduledDto.NinetyOneToOneHundredFive;
import com.bc.revan.Entities.Dto.StatisticsForTeamScheduledDtos.GoalsForStatisticsScheduledDto.OneHundredSixToOneHundredTwenty;
import com.bc.revan.Entities.Dto.StatisticsForTeamScheduledDtos.GoalsForStatisticsScheduledDto.SeventySixToNinety;
import com.bc.revan.Entities.Dto.StatisticsForTeamScheduledDtos.GoalsForStatisticsScheduledDto.SixteenToThirty;
import com.bc.revan.Entities.Dto.StatisticsForTeamScheduledDtos.GoalsForStatisticsScheduledDto.SixtyOneToSeventyFive;
import com.bc.revan.Entities.Dto.StatisticsForTeamScheduledDtos.GoalsForStatisticsScheduledDto.ThirtyOneToFortyFive;
import com.bc.revan.Entities.Dto.StatisticsForTeamScheduledDtos.GoalsForStatisticsScheduledDto.ZeroToFifteen;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class CardsForStatisticsScheduledDto {

	Yellow yellow;
	Red red;
	
	@Data
	public static class Yellow{		
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
	public static class Red{		
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
