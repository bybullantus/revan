package com.bc.revan.Entities.Dto;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public class BirthDto implements Serializable{

	Date date;	

	String place;
	
	String country;
}
