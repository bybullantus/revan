package com.bc.revan.Entities.Dto;

import java.io.Serializable;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public class CardsOfStatisticsForPlayersDto implements Serializable{

	int yellow;
	int red;

}
