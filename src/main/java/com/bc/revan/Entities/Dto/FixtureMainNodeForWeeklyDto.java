package com.bc.revan.Entities.Dto;

import java.io.Serializable;

import com.bc.revan.Entities.Fixture;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public class FixtureMainNodeForWeeklyDto implements Serializable{
	Fixture fixture;

	SeasonDto season;

	TeamsMainNodeOfFixtureDto teamsMainNode;

	GoalsOfFixtureDto goals;

	String round;
}
