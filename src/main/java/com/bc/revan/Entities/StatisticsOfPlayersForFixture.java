package com.bc.revan.Entities;

import com.bc.revan.Entities.Base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "statisticsofplayersforfixture",indexes = {
		  @Index(columnList = "id", name = "statisticofplayersforfixture_id_hidx"),
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class StatisticsOfPlayersForFixture extends BaseEntity{
	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@SequenceGenerator(name = "statisticsofplayersforfixture_seq_gen", sequenceName = "statisticsofplayersforfixture.gen", initialValue = 1, allocationSize = 1)
	@Setter(AccessLevel.NONE)
	long id;
	
	@ManyToOne
	@JoinColumn(name = "gamesid")
	GamesOfStatisticsForPlayers games;
	
	@JsonProperty("offsides")
	@Nullable
	@Column(name = "offsides")
	int offsides;
	
	@ManyToOne
	@JoinColumn(name = "shotsid")
	ShotsOfStatisticsForPlayers shots;
	
	@ManyToOne
	@JoinColumn(name = "goalsid")
	GoalsOfStatisticsForPlayers goals;
	
	@ManyToOne
	@JoinColumn(name = "passesid")
	PassesOfStatisticsForPlayers passes;
	
	@ManyToOne
	@JoinColumn(name = "tacklesid")
	TacklesOfStatisticsForPlayers tackles;
	
	@ManyToOne
	@JoinColumn(name = "duelsid")
	DuelsOfStatisticsForPlayers duels;
	
	@ManyToOne
	@JoinColumn(name = "dribblesid")
	DribblesOfStatisticsForPlayers dribbles;
	
	@ManyToOne
	@JoinColumn(name = "foulsid")
	FoulsOfStatisticsForPlayers fouls;
	
	@ManyToOne
	@JoinColumn(name = "cardsid")
	CardsOfStatisticsForPlayers cards;
	
	@ManyToOne
	@JoinColumn(name = "penaltyid")
	PenaltyOfStatisticsForPlayers penalty;
	
}
