package com.bc.revan.Entities.Dto;

import java.io.Serializable;
import java.util.List;



import jakarta.persistence.MappedSuperclass;

import lombok.Data;

@Data
@MappedSuperclass
public class PenaltyOfStatisticsDto implements Serializable{

    int total;
	
	List<ScoredAndMissedOfPenaltyDto> scoredAndMissedOfPenalty;
}
