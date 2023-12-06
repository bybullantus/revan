package com.bc.revan.Entities;

import java.util.List;

import com.bc.revan.Entities.Base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "lineupforfixture",indexes = {
		  @Index(columnList = "id", name = "lineupforfixture_id_hidx"),
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LineupForFixture extends BaseEntity{
	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@SequenceGenerator(name = "lineupforfixture_seq_gen", sequenceName = "lineupforfixture.gen", initialValue = 1, allocationSize = 1)
	@Setter(AccessLevel.NONE)
	long id;

	@ManyToOne
	@JoinColumn(name = "teamid")
	Team team;
	
	@JsonProperty("Players Color")
	@ManyToOne
	@JoinColumn(name = "colorsofplayerid")
	ColorOfLineup colorsOfPlayer;
	
	@JsonProperty("Goalkeeper Color")
	@ManyToOne
	@JoinColumn(name = "colorsofgoalkeeperid")
	ColorOfLineup colorsOfGoalkeeper;
	
	@ManyToOne
	@JoinColumn(name = "coachid")
	Coachs coach;
	
	@JsonProperty("formation")
	@Column(name = "formation", length = 10)
	String formation;
		
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "startXI_lineupforfixtureid")
	List<StartXIAndSubsOfLineup> startXI;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "subs_lineupforfixtureid")
	List<StartXIAndSubsOfLineup> substitutes;
	
	@ManyToOne
	@JoinColumn(name = "fixtureid")
	Fixture fixture;
	}
