package com.bc.revan.Entities;

import java.math.BigDecimal;

import com.bc.revan.Entities.Base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonProperty;

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

@Entity
@Table(name = "failedtoscoreofstatistics",indexes = {
		  @Index(columnList = "id", name = "failedtoscoreofstatistics_id_hidx"),
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FailedToScoreOfStatistics extends BaseEntity{

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@SequenceGenerator(name = "failedtoscoreofstatistics", sequenceName = "failedtoscoreofstatistics.gen", initialValue = 1, allocationSize = 1)
	@Setter(AccessLevel.NONE)
	long id;
	
	@JsonProperty("home")
	@Column(name = "home" , precision = 5, scale = 2)
	int home;
	
	@JsonProperty("away")
	@Column(name = "away" , precision = 5, scale = 2)
	int away;
	
	@JsonProperty("total")
    @Column(name = "total", precision = 5, scale = 2)
	int total;
}
