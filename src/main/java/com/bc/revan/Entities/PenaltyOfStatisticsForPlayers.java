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
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "penaltyofstatisticsforplayers",indexes = {
		  @Index(columnList = "id", name = "penaltyofstatisticsforplayers_id_hidx"),
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class PenaltyOfStatisticsForPlayers extends BaseEntity{

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@SequenceGenerator(name = "penaltyofstatisticsforplayers_seq_gen", sequenceName = "penaltyofstatisticsforplayers.gen", initialValue = 1, allocationSize = 1)
	@Setter(AccessLevel.NONE)
	long id;

	@JsonProperty("won")
	@Nullable
	@Column(name = "won")
	int won;
	
	@JsonProperty("commited")
	@Nullable
	@Column(name = "commited")
	int commited;
	
	@JsonProperty("scored")
	@Nullable
	@Column(name = "scored")
	int scored;
	
	@JsonProperty("missed")
	@Nullable
	@Column(name = "missed")
	int missed;
	
	@JsonProperty("saved")
	@Nullable
	@Column(name = "saved")
	int saved;
	
}
