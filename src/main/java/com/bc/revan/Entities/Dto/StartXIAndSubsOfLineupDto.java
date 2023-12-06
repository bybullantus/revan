package com.bc.revan.Entities.Dto;

import java.io.Serializable;

import com.bc.revan.Entities.Player;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public class StartXIAndSubsOfLineupDto implements Serializable{
	PlayerDto player;
	String position;
	int number;
	String grid;
}
