package com.bc.revan.Entities.Dto;

import java.io.Serializable;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public class DribblesOfStatisticsForPlayersDto implements Serializable{
	int attempts;
	int success;
	int past;

}
