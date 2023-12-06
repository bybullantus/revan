package com.bc.revan.Entities.Dto;

import java.io.Serializable;
import java.util.List;

import com.bc.revan.Entities.AllHomeAwayOfStandingDetail;
import com.bc.revan.Entities.Team;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@MappedSuperclass

public class StandingDetailDto implements Serializable{
	
	int rank;
	
	TeamDto team;
	
	int points;
	
	int goalsDiff;
	
	String group;
	
	String form;
	
	String status;
	
	String description;

	String update;
	
	List<AllHomeAwayOfStandingDetailDto> allHomeAwayOfStandingDetail;
}
