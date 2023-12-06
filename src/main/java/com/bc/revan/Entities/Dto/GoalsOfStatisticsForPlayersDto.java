package com.bc.revan.Entities.Dto;

import java.io.Serializable;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public class GoalsOfStatisticsForPlayersDto implements Serializable{

	int total;
	int conceded;
	int assists;
	int saves;


}
