package com.bc.revan.Entities.Dto;

import java.io.Serializable;
import java.util.List;

import com.bc.revan.Entities.FixtureMainNode;

import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@MappedSuperclass
public class LiveMatchesDto implements Serializable {

	long id;
	
	FixtureForLiveMatchesDto fixture;

	LeagueForLiveMatchesDto league;

	TeamMainForLiveMatchesDto teams;
	
	List<OddsForLiveMatchesDto> odds;
	
	boolean isFire=false;
	
	double odd;
	
	FixtureMainNodeDto fixtureMainNode;
}
