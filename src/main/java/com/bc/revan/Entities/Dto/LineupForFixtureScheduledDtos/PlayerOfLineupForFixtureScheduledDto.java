package com.bc.revan.Entities.Dto.LineupForFixtureScheduledDtos;

import java.io.Serializable;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public class PlayerOfLineupForFixtureScheduledDto implements Serializable{
	
	PlayerData player;
	
	
	@Data
	public static class PlayerData {

		long id;
		String name;
		int number;
		String pos;
		String grid;
		

	}
}
