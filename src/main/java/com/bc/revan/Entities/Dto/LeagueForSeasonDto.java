package com.bc.revan.Entities.Dto;

import java.io.Serializable;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public class LeagueForSeasonDto implements Serializable {
	
	long id;
	String name;	
	String type;	
	String logo;
	

}
