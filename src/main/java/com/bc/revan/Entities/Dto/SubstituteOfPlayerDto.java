package com.bc.revan.Entities.Dto;

import com.bc.revan.Entities.Base.BaseEntity;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public class SubstituteOfPlayerDto extends BaseEntity{
	int in;
	
	int out;
	
	int bench;
}
