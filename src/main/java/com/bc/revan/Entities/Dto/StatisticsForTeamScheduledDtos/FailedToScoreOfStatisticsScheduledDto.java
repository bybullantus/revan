package com.bc.revan.Entities.Dto.StatisticsForTeamScheduledDtos;

import java.io.Serializable;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public class FailedToScoreOfStatisticsScheduledDto implements Serializable {
	
	int home;
	int away;
	int total;
}
