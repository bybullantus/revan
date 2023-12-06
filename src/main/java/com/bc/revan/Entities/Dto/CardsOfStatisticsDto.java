
package com.bc.revan.Entities.Dto;

import java.io.Serializable;
import java.util.List;

import com.bc.revan.Entities.Enums.EnumForYellowAndRed;

import jakarta.persistence.MappedSuperclass;

import lombok.Data;


@Data
@MappedSuperclass
public class CardsOfStatisticsDto implements Serializable{

	List<YellowAndRedOfCardsDto> yellowAndRedOfCards;
	
	@Data
	public static class YellowAndRedOfCardsDto{		
		
	List<TimeIntervalDto> timeIntervals;

	EnumForYellowAndRed yellowAndRed;
	}
}
