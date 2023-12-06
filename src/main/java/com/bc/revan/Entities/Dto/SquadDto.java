package com.bc.revan.Entities.Dto;

import java.io.Serializable;
import java.util.List;

import com.bc.revan.Entities.Player;
import com.bc.revan.Entities.Team;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public class SquadDto implements Serializable{

	
	TeamForInjuriesDto team;
	
	List<PlayerInSquadDto> players;
}
