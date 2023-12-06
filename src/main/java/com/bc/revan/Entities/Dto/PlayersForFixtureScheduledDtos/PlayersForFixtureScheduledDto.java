package com.bc.revan.Entities.Dto.PlayersForFixtureScheduledDtos;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public class PlayersForFixtureScheduledDto implements Serializable{
	
PlayerOfPlayersForFixtureScheduledDto player;
List<StatisticsOfPlayersForFixtureScheduledDto> statistics;	
	
	
}
