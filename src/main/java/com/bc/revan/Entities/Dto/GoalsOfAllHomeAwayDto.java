package com.bc.revan.Entities.Dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public class GoalsOfAllHomeAwayDto implements Serializable{
	
	@JsonProperty("for")
	int forofgoals;
	
	int against;
}
