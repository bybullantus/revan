package com.bc.revan.Entities.Dto;

import java.io.Serializable;

import com.bc.revan.Entities.Venue;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public class VenueDto implements Serializable{
	
	String name;
	
	
	String address;
	
	
	String city;
	
	
	int capacity;
	
	
	String surface;
	
	String image;
}
