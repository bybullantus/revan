package com.bc.revan.Entities.Dto;

import java.io.Serializable;

import com.bc.revan.Entities.CardsOfStatisticsForPlayers;
import com.bc.revan.Entities.DribblesOfStatisticsForPlayers;
import com.bc.revan.Entities.DuelsOfStatisticsForPlayers;
import com.bc.revan.Entities.FoulsOfStatisticsForPlayers;
import com.bc.revan.Entities.GamesForStatisticsOfPlayer;
import com.bc.revan.Entities.GoalsOfStatisticsForPlayers;
import com.bc.revan.Entities.PassesOfStatisticsForPlayers;
import com.bc.revan.Entities.PenaltyOfStatisticsForPlayers;
import com.bc.revan.Entities.Season;
import com.bc.revan.Entities.ShotsOfStatisticsForPlayers;
import com.bc.revan.Entities.SubstituteOfPlayer;
import com.bc.revan.Entities.TacklesOfStatisticsForPlayers;
import com.bc.revan.Entities.Team;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public class StatisticsOfPlayerDto implements Serializable{

	TeamDto team;


	SeasonDto season;
	
	
	GamesForStatisticsOfPlayerDto games; //yeni
	
	
	SubstituteOfPlayerDto substitutes; //yeni
	
	
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
