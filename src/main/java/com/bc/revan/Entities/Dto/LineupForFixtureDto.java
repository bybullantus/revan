package com.bc.revan.Entities.Dto;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public class LineupForFixtureDto implements Serializable{
	
	TeamDto team;
	ColorOfLineupDto colorsOfPlayer;
	ColorOfLineupDto colorsOfGoalkeeper;
	CoachsWithOutCareerDto coach;
	String formation;
	List<StartXIAndSubsOfLineupDto> startXI;
	List<StartXIAndSubsOfLineupDto> substitutes;
	FixtureDto fixture;
	
}
