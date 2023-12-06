package com.bc.revan.Entities.Dto.StatisticsForTeamScheduledDtos;

import java.io.Serializable;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public class PenaltyForStatisticsScheduledDto implements Serializable {

	int total;
	Scored scored;
	Missed missed;
	
	@Data
	public static class Scored{		
	
		int total;
		String percentage;
		
	}
	@Data
	public static class Missed{		
	
		int total;
		String percentage;
		
	}
}
