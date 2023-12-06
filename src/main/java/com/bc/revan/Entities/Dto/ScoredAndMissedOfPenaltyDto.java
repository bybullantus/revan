package com.bc.revan.Entities.Dto;

import java.io.Serializable;

import com.bc.revan.Entities.Enums.EnumForScoredAndMissed;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public class ScoredAndMissedOfPenaltyDto implements Serializable{

	
	int total;	
	String percentage;
	EnumForScoredAndMissed scoredAndMissedOfPenalty;
}
