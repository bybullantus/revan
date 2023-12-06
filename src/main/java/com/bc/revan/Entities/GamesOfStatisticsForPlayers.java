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

@Table(name = "gamesofstatisticsforplayers",indexes = {
		  @Index(columnList = "id", name = "gamesofstatisticsforplayers_id_hidx"),
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class GamesOfStatisticsForPlayers extends BaseEntity{
	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@SequenceGenerator(name = "gamesofstatisticsforplayers_seq_gen", sequenceName = "gamesofstatisticsforplayers.gen", initialValue = 1, allocationSize = 1)
	@Setter(AccessLevel.NONE)
	long id;
	
	@JsonProperty("minutes")
	@Nullable
	@Column(name = "minutes")
	int minutes;
	
	@JsonProperty("number")
	@Nullable
	@Column(name = "number")
	int number;
	
	@JsonProperty("position")
	@Nullable
	@Column(name = "position", length = 1)
	String position;
	
	@JsonProperty("rating")
	@Nullable
	@Column(name = "rating", length = 5)
	String rating;
	
	@JsonProperty("captain")
	@Nullable
	@Column(name = "captain")
	boolean captain;
	
	@JsonProperty("substitute")
	@Nullable
	@Column(name = "substitute")
	boolean substitute;
}
