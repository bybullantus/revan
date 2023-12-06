package com.bc.revan.Entities.Dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@MappedSuperclass
public class StatusForLiveMatchesDto  implements Serializable{
	
	@JsonProperty("long")
	String longForStatus;
	
	int elapsed; 
	
	String seconds;
}