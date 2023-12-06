package com.bc.revan.Entities.Dto;

import java.io.Serializable;

import jakarta.persistence.MappedSuperclass;

import lombok.Data;


@Data
@MappedSuperclass
public class StreakOfBiggestDto implements Serializable{

	int wins;

	int loses;

	int draws;
}
