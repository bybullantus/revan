package com.bc.revan.Entities.Dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.bc.revan.Entities.Minute;
import com.bc.revan.Entities.TotalAndAverage;
import com.bc.revan.Entities.Enums.EnumForForAndAgainst;
import com.bc.revan.Entities.Enums.EnumForTotalAndAverage;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public class TotalAndAverageDto implements Serializable{


	BigDecimal home;
	

	BigDecimal away;
	

    BigDecimal total;
	
	EnumForTotalAndAverage totalAndAverage;
}
