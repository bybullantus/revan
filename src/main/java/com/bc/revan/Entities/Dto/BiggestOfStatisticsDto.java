package com.bc.revan.Entities.Dto;

import java.io.Serializable;
import java.util.List;

import com.bc.revan.Entities.GoalsOfBiggest;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public class BiggestOfStatisticsDto implements Serializable{

	
	StreakOfBiggestDto streakOfBiggest;
	
	List<WinsAndLosesOfBiggestDto> winsAndLosesOfBiggest;
	
	GoalsOfBiggestDto goalsOfBiggest;
	
}
