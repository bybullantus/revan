package com.bc.revan.Entities.Dto;

import java.io.Serializable;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public class InjuriesDto implements Serializable{

	PlayerForInjuriesDto player;
	TeamForInjuriesDto team;
	FixtureForInjuriesDto fixture;
	LeagueForGetAllDto league;
	SeasonDto season;
	String type;
	String reason;
}
