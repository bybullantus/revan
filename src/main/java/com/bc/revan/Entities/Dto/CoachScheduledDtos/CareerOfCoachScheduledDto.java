package com.bc.revan.Entities.Dto.CoachScheduledDtos;

import java.io.Serializable;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public class CareerOfCoachScheduledDto implements Serializable {

	TeamData team;

	String start;

	String end;

	@Data
	public static class TeamData {

		long id;
		String name;
		String logo;

	}

}
