package com.bc.revan.Entities.Dto.StandingScheduledDtos;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public class StandingForStandingsScheduledDto implements Serializable{
	int rank;
	TeamForScheduled team;
	int points;
	int goalsDiff;
	String group;
	String form;
	String status;
	String description;
	All all;
	Home home;
	Away away;
	String update;

	@Data
	public static class TeamForScheduled{		
	
		long id;
		String name;	
		String logo;	
		
	}
	
	@Data
	public static class All{		
	
		int played;
		int win;
		int draw;
		int lose;	
		Goals goals;
		
	}
	@Data
	public static class Home{		
	
		int played;
		int win;
		int draw;
		int lose;	
		Goals goals;
		
	}
	@Data
	public static class Away{		
	
		int played;
		int win;
		int draw;
		int lose;	
		Goals goals;
		
	}
	@Data
	public static class Goals{		
		@JsonProperty("for")
		int forOfGols;
		
		int against;
		
	}
}
