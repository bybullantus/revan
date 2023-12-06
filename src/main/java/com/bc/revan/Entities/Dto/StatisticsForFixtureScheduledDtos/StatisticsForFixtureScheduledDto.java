package com.bc.revan.Entities.Dto.StatisticsForFixtureScheduledDtos;

import java.io.Serializable;
import java.util.List;

import com.bc.revan.Entities.Dto.StandingScheduledDtos.LeagueForStandingsScheduledDto;
import com.bc.revan.Entities.Dto.StandingScheduledDtos.StandingForStandingsScheduledDto;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public class StatisticsForFixtureScheduledDto implements Serializable{
	String type;
	String value;

}
