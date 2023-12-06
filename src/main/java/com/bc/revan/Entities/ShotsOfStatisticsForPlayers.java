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

@Table(name = "shotsofstatisticsforplayers",indexes = {
		  @Index(columnList = "id", name = "shotsofstatisticsforplayers_id_hidx"),
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class ShotsOfStatisticsForPlayers extends BaseEntity{
	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@SequenceGenerator(name = "shotsofstatisticsforplayers_seq_gen", sequenceName = "shotsofstatisticsforplayers.gen", initialValue = 1, allocationSize = 1)
	@Setter(AccessLevel.NONE)
	long id;
	
	@JsonProperty("total")
	@Nullable
	@Column(name = "total")
	int total;
	
	@JsonProperty("on")
	@Nullable
	@Column(name = "onofshots")
	int on;
}
