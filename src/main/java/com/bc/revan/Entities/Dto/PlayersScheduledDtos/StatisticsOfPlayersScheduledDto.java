package com.bc.revan.Entities.Dto.PlayersScheduledDtos;

import java.io.Serializable;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public class StatisticsOfPlayersScheduledDto implements Serializable{
	
	TeamData team;
	LeagueData league;
	GamesData games;
	SubstitutesData substitutes;
	ShotsData shots;
	GoalsData goals;
	PassesData passes;
	TacklesData tackles;
	DuelsData duels;
	DribblesData dribbles;
	FoulsData fouls;
	CardsData cards;
	PenaltyData penalty;
	
	@Data
	public static class LeagueData {

		int id;
		String name;
		String country;
		String logo;	
		String flag;	
		int season;
	}
	
	@Data
	public static class TeamData {

		int id;
		String name;
		String logo;	
	}
	@Data
	public static class GamesData {
		int appearences;
		int lineups;
		int minutes;
		int number;
		String position;
		String rating;	
		boolean captain;
	
	}
	@Data
	public static class SubstitutesData {

		int in;
		int out;		
		int bench;

	}
	@Data
	public static class ShotsData {

		int total;
		int on;		

	}
	@Data
	public static class GoalsData {

		int total;
		int conceded;		
		int assists;
		int saves;

	}
	@Data
	public static class PassesData {

		int total;
		int key;		
		String accuracy;

	}
	@Data
	public static class TacklesData {

		int total;
		int blocks;		
		int interceptions;

	}
	@Data
	public static class DuelsData {

		int total;
		int won;		

	}
	@Data
	public static class DribblesData {

		int attempts;
		int success;		
		int past;		

	}
	@Data
	public static class FoulsData {

		int drawn;
		int committed;		

	}
	@Data
	public static class CardsData {

		int yellow;
		int yellowred;
		int red;		

	}
	@Data
	public static class PenaltyData {

		int won;
		int commited;		
		int scored;		
		int missed;		
		int saved;		

	}
}
