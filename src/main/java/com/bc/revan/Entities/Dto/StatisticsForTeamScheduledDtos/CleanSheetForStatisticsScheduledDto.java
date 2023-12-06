package com.bc.revan.Entities.Dto.StatisticsForTeamScheduledDtos;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public class CleanSheetForStatisticsScheduledDto implements Serializable{
	
	int home;
	int away;
	int total;
}
