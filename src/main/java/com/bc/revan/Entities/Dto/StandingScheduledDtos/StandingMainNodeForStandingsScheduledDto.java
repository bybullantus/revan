package com.bc.revan.Entities.Dto.StandingScheduledDtos;

import java.io.Serializable;
import java.util.List;


import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public class StandingMainNodeForStandingsScheduledDto implements Serializable{
	
	@JsonProperty("")
	List<StandingForStandingsScheduledDto> standings;
	
	
	
}
