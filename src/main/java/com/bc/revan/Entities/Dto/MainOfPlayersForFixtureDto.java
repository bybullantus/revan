package com.bc.revan.Entities.Dto;

import java.io.Serializable;
import java.util.List;

import com.bc.revan.Entities.PlayersForFixture;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;


@Data
@MappedSuperclass
public class MainOfPlayersForFixtureDto implements Serializable {
	
	TeamForInjuriesDto team;
		
	FixtureForInjuriesDto fixture;
	
	List<PlayersForFixtureDto> players;
}
