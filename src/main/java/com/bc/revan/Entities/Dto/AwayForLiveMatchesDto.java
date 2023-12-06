package com.bc.revan.Entities.Dto;

import java.io.Serializable;

import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@MappedSuperclass
public class AwayForLiveMatchesDto implements Serializable{
	long id;
	String name;
	int goals;
}
