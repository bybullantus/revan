package com.bc.revan.Entities.Dto;


import java.io.Serializable;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public class SeasonDto implements Serializable {
	
	int year;
	String start;
	String end;
	boolean current;
	
	LeagueForSeasonDto league;
}
