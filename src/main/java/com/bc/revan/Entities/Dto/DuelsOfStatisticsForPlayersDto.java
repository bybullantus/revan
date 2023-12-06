package com.bc.revan.Entities.Dto;

import java.io.Serializable;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public class DuelsOfStatisticsForPlayersDto implements Serializable{
	int total;
	int won;

}
