package com.bc.revan.Entities.Dto;

import java.io.Serializable;
import java.util.List;

import com.bc.revan.Entities.Minute;
import com.bc.revan.Entities.TimeInterval;
import com.bc.revan.Entities.Enums.EnumForForAndAgainst;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public class MinuteDto implements Serializable{

	List<TimeIntervalDto> timeIntervals;
}
