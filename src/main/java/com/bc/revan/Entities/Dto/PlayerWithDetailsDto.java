package com.bc.revan.Entities.Dto;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public class PlayerWithDetailsDto implements Serializable{
	long id;
	
	String name;
	
	String firstName;	

	String lastName;
	
	BirthDto birth;
	
	String nationality;
	
	String height;
	
	String weight;
	
	Boolean injured;
	
	String photo;
	
	List<StatisticsOfPlayerDto> statistics;
}
