package com.bc.revan.Entities.Dto;

import java.io.Serializable;
import java.util.List;

import com.bc.revan.Entities.Fixture;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public class StatisticsForFixtureDto implements Serializable{
	
	TeamDto team;
	
	FixtureDto fixture;
	
	List<StatisticsDetailsForFixtureDto> statisticsDetailsForFixture;
}
