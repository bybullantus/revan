package com.bc.revan.Entities.Dto;

import java.io.Serializable;


import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public class PenaltyOfStatisticsForPlayersDto implements Serializable{
	int won;
	int commited;
	int scored;
	int missed;
	int saved;

}
