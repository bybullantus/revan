package com.bc.revan.Entities.Dto;

import java.io.Serializable;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public class CoachsWithOutCareerDto implements Serializable{
		String name;	  
	    String nationality;	 
		String photo;
}
