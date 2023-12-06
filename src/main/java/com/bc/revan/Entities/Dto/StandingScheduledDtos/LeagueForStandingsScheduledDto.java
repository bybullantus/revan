package com.bc.revan.Entities.Dto.StandingScheduledDtos;

import java.io.Serializable;
import java.util.List;

import com.bc.revan.Entities.Dto.StatisticsForTeamScheduledDtos.PenaltyForStatisticsScheduledDto;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public class LeagueForStandingsScheduledDto implements Serializable{

	long id;
	String name;
	String country;
	String logo;	
	String flag;
	int season;
	@JsonProperty("standings")
	List<List<StandingForStandingsScheduledDto>> standingMainNode;
	

}
