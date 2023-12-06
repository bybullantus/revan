package com.bc.revan.Entities.Dto;

import java.io.Serializable;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public class ColorOfLineupDto implements Serializable{
	String primary;
	String number;
	String border;
}
