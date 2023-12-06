package com.bc.revan.Entities.Dto;

import java.io.Serializable;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public class EventsForFixtureDto implements Serializable {
String type;
	
	
	String detail;
	
	String comments;
	
	TimeOfEventDto time;
	
	TeamDto team;
	
	PlayerForEventDto player;
	
	PlayerForEventDto assist;
}
