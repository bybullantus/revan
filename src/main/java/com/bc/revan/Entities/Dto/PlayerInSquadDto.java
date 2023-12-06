package com.bc.revan.Entities.Dto;

import java.io.Serializable;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public class PlayerInSquadDto implements Serializable {
	long id;
	
	String name;
	
	int number;
	
	String position;
	
	String photo;
	
}
