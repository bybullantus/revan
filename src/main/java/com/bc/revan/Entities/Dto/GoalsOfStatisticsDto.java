package com.bc.revan.Entities.Dto;

import java.io.Serializable;
import java.util.List;

import com.bc.revan.Entities.ForAndAgainstOfGoals;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public class GoalsOfStatisticsDto implements Serializable {


	List<ForAndAgainstOfGoalsDto> forAndAgainstOfGoals;

}
