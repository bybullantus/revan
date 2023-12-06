package com.bc.revan.Entities.Dto;

import java.io.Serializable;


import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public class GamesOfStatisticsForPlayersDto implements Serializable{

	int minutes;
	
	int number;
	
	String position;
	
	String rating;

	boolean captain;
	
	boolean substitute;

}
