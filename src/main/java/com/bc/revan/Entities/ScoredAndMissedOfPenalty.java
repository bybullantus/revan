package com.bc.revan.Entities;

import com.bc.revan.Entities.Base.BaseEntity;
import com.bc.revan.Entities.Enums.EnumForScoredAndMissed;
import com.bc.revan.Entities.Enums.EnumForTimeInterval;
import com.bc.revan.Entities.Enums.EnumForWinsAndLoses;
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

@Table(name = "scoredandmissedofpenalty",indexes = {
		  @Index(columnList = "id", name = "scoredandmissedofpenalty_id_hidx"),
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class ScoredAndMissedOfPenalty extends BaseEntity{

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@SequenceGenerator(name = "scoredandmissedofpenalty", sequenceName = "scoredandmissedofpenalty.gen", initialValue = 1, allocationSize = 1)
	@Setter(AccessLevel.PRIVATE)
	long id;
	
	@JsonProperty("total")
	@Column(name = "total")
	int total;
	
	@JsonProperty("percentage")
	@Column(name = "percentage", length = 20)
	String percentage;
	
	@JsonProperty("scoredandmissedofpenalty")
	@Column(name = "scoredandmissedofpenalty")
	EnumForScoredAndMissed scoredAndMissedOfPenalty;
	
}
