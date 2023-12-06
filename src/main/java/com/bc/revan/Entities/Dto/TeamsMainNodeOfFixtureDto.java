package com.bc.revan.Entities.Dto;

import java.io.Serializable;
import java.util.List;


import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public class TeamsMainNodeOfFixtureDto implements Serializable{

	TeamDto teamHome;
	
	TeamDto teamAway;
}
