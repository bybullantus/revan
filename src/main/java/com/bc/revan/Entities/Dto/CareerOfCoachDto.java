package com.bc.revan.Entities.Dto;

import java.io.Serializable;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public class CareerOfCoachDto implements Serializable {
    TeamForInjuriesDto team;
    String end;
    String start;
}
