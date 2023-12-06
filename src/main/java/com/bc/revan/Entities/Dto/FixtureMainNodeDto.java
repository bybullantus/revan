package com.bc.revan.Entities.Dto;

import java.io.Serializable;

import com.bc.revan.Entities.Fixture;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public class FixtureMainNodeDto implements Serializable {

	LeagueForGetAllDto league;

	Fixture fixture;

	SeasonDto season;

	TeamsMainNodeOfFixtureDto teamsMainNode;

	GoalsOfFixtureDto goals;

	MainNodeOfScoreForFixtureDto score;

	String round;

}
