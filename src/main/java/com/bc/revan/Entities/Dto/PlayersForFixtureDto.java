package com.bc.revan.Entities.Dto;

import java.io.Serializable;
import java.util.List;

import com.bc.revan.Entities.StatisticsOfPlayersForFixture;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public class PlayersForFixtureDto implements Serializable {
	PlayerForInjuriesDto player;
	
	List<StatisticsOfPlayersForFixtureDto> statistics;
}
