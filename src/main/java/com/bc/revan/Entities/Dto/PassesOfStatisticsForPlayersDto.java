package com.bc.revan.Entities.Dto;

import java.io.Serializable;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public class PassesOfStatisticsForPlayersDto implements Serializable{
	int total;
	int key;
	String accuracy;

}
