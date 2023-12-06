package com.bc.revan.Entities.Dto;

import java.io.Serializable;
import java.util.List;

import com.bc.revan.Entities.Enums.EnumForForAndAgainst;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public class ForAndAgainstOfBiggestDto implements Serializable{


	String home;
	
	String away;
	
	EnumForForAndAgainst forAndAgainst;
	
}
