package com.bc.revan.Entities.Dto;

import java.io.Serializable;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public class StatisticsOfPlayersForFixtureDto implements Serializable{
	
	GamesOfStatisticsForPlayersDto games;
	
	int offsides;
	
	ShotsOfStatisticsForPlayersDto shots;
	
	GoalsOfStatisticsForPlayersDto goals;
	
	PassesOfStatisticsForPlayersDto passes;
	
	TacklesOfStatisticsForPlayersDto tackles;
	
	DuelsOfStatisticsForPlayersDto duels;
	
	DribblesOfStatisticsForPlayersDto dribbles;
	
	FoulsOfStatisticsForPlayersDto fouls;
	
	CardsOfStatisticsForPlayersDto cards;
	
	PenaltyOfStatisticsForPlayersDto penalty;
}
