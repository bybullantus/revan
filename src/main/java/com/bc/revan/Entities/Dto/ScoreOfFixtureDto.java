package com.bc.revan.Entities.Dto;

import java.io.Serializable;
import java.util.List;

import com.bc.revan.Entities.Enums.EnumForTimeOfScore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public class ScoreOfFixtureDto implements Serializable{

	int home;	
	int away;	
	EnumForTimeOfScore timeOfScore;
}
