package com.bc.revan.Entities.Dto;

import java.io.Serializable;

import com.bc.revan.Entities.Venue;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public class TeamDto implements Serializable{

	String name;

	String code;
	
	String country;
	
	int founded;
	
	boolean national;
	
	String logo;
	
	VenueDto venue;
	
}
