package com.bc.revan.Entities.Dto;

import java.io.Serializable;

import com.bc.revan.Entities.FixtureOfStatistics;
import com.bc.revan.Entities.Enums.EnumForPWDL;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.SequenceGenerator;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
@MappedSuperclass
public class PWDLFixtureDto implements Serializable{

	
	int home;
	
	int away;	
	
	int total;
	
	EnumForPWDL pwdl;
}
