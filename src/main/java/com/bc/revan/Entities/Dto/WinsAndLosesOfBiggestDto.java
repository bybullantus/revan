package com.bc.revan.Entities.Dto;

import java.io.Serializable;

import com.bc.revan.Entities.Enums.EnumForWinsAndLoses;

import jakarta.persistence.MappedSuperclass;

import lombok.Data;

@Data
@MappedSuperclass
public class WinsAndLosesOfBiggestDto  implements Serializable{
	
	String home;
	String away;
	EnumForWinsAndLoses winsAndLoses;
	
}
