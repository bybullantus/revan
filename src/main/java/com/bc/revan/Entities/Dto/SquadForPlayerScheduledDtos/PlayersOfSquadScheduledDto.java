package com.bc.revan.Entities.Dto.SquadForPlayerScheduledDtos;

import java.io.Serializable;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public class PlayersOfSquadScheduledDto implements Serializable{
	long id;
	String name;
	String age;
	int number;
	String position;
	String photo;
}
