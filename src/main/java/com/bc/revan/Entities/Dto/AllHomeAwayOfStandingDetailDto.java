package com.bc.revan.Entities.Dto;

import java.io.Serializable;
import java.util.List;

import com.bc.revan.Entities.GoalsOfAllHomeAway;
import com.bc.revan.Entities.Enums.EnumForAllHomeAway;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@MappedSuperclass
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AllHomeAwayOfStandingDetailDto implements Serializable {

	int played;
	int win;
	int draw;
	int lose;
	GoalsOfAllHomeAwayDto goalsOfAllHomeAway;
	EnumForAllHomeAway allHomeAway;
}
