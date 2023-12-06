package com.bc.revan.Entities.Dto.StatisticsForTeamScheduledDtos;

import java.io.Serializable;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public class LeagueForStatisticsScheduledDto implements Serializable{
	
	long id;
	String name;	
	String logo;	
	String country;
	int season;
	String flag;
}
