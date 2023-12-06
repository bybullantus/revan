package com.bc.revan.Entities.Dto;

import java.io.Serializable;

import com.bc.revan.Entities.Enums.EnumForBetType;
import com.bc.revan.Entities.Enums.EnumForStatus;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public class WeeklyMatchDto implements Serializable{
	
	FixtureMainNodeForWeeklyDto fixtureMainNode;
	
	EnumForBetType betType;
	
	EnumForStatus status;
}
