package com.bc.revan.Entities;

import java.util.List;

import com.bc.revan.Entities.Base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.annotation.Nullable;
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
@Table(name = "statisticsforteam",indexes = {
		  @Index(columnList = "id", name = "statisticsforteam_id_hidx"),
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StatisticForTeam extends BaseEntity{

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@SequenceGenerator(name = "statistics", sequenceName = "statistics.gen", initialValue = 1, allocationSize = 1)
	@Setter(AccessLevel.NONE)
	long id;
	
	@ManyToOne
	@JoinColumn(name = "leagueid")
	League league;
	
	@ManyToOne
	@JoinColumn(name = "teamid")
	Team team;
	
	@JsonProperty("year")
	@Column(name = "year")
	int year;
	
	@ManyToOne
	@JoinColumn(name = "seasonid")
	Season season;
	
	@JsonProperty("form")
	@Column(name = "form", length = 100)
	String form;
	
	@ManyToOne
	@JoinColumn(name = "fixtureofstatisticsid")
	FixtureOfStatistics fixtureOfStatistics;
	
	@ManyToOne
	@JoinColumn(name = "goalsofstatisticsid")
	GoalsOfStatistics goalsOfStatistics;

	@ManyToOne
	@JoinColumn(name = "biggestofstatisticsid")
	BiggestOfStatistics biggestOfStatistics;
	
	@ManyToOne
	@JoinColumn(name = "cleansheetofstatisticsid")
	CleanSheetOfStatistics cleanSheetOfStatistics;

	@ManyToOne
	@JoinColumn(name = "failedtoscoreofstatisticsid")
	FailedToScoreOfStatistics failedToScoreOfStatistics;
	
	@ManyToOne
	@JoinColumn(name = "penaltyofstatisticsid")
	PenaltyOfStatistics penaltyOfStatistics;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "statisticforteamid")
	List<LineupOfStatistics> LineupOfStatistics;
	
	@ManyToOne
	@JoinColumn(name = "cardsofstatisticsid")
	CardsOfStatistics  cardsOfStatistics;

}
