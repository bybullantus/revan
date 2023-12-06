package com.bc.revan.Entities.Dto;

import java.io.Serializable;
import java.util.List;

import com.bc.revan.Entities.CardsOfStatistics;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;


@Data
@MappedSuperclass
public class StatisticForTeamDto implements Serializable{
	
	
	LeagueForGetAllDto league;
	
	TeamDto team;
	
	int year;
	
	SeasonDto season;
	
	String form;
	
	FixtureOfStatisticsDto fixtureOfStatistics;
	
	GoalsOfStatisticsDto goalsOfStatistics;
	
	BiggestOfStatisticsDto  biggestOfStatistics;
	
	CleanSheetOfStatisticsDto cleanSheetOfStatistics;
	
	FailedToScoreOfStatisticsDto failedToScoreOfStatistics;
	
	PenaltyOfStatisticsDto penaltyOfStatistics;
	
	List<LineupOfStatisticsDto> lineupOfStatistics;
	
	CardsOfStatisticsDto  cardsOfStatistics;
}
