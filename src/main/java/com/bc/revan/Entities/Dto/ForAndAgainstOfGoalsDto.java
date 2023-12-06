package com.bc.revan.Entities.Dto;

import java.io.Serializable;
import java.util.List;

import com.bc.revan.Entities.ForAndAgainstOfGoals;
import com.bc.revan.Entities.Minute;
import com.bc.revan.Entities.TotalAndAverage;
import com.bc.revan.Entities.Enums.EnumForForAndAgainst;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@MappedSuperclass
public class ForAndAgainstOfGoalsDto implements Serializable{

	EnumForForAndAgainst ForAndAgainst;
	
	List<TotalAndAverageDto> totalAndAverage;
		
	MinuteDto minute;
}
