package com.bc.revan.Entities.Dto;

import com.bc.revan.Entities.Base.BaseEntity;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public class GamesForStatisticsOfPlayerDto extends BaseEntity{
int appearences;
	

	int lineups;
	
	
	int minutes;
	
	
	int number;
	

	String position;
	
	
	String rating;
	
	boolean captain;
}
