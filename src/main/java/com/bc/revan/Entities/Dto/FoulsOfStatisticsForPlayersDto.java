package com.bc.revan.Entities.Dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public class FoulsOfStatisticsForPlayersDto implements Serializable{

	int drawn;
	
	int committed;
}
