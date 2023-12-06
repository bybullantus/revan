package com.bc.revan.Entities.Dto;

import java.io.Serializable;

import com.bc.revan.Entities.Birth;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public class PlayerDto implements Serializable {

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
	
	
}
