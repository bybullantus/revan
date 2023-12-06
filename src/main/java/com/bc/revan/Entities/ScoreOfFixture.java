package com.bc.revan.Entities;

import com.bc.revan.Entities.Base.BaseEntity;
import com.bc.revan.Entities.Enums.EnumForPWDL;
import com.bc.revan.Entities.Enums.EnumForTimeOfScore;
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
@Table(name = "scoreoffixture",indexes = {
		   @Index(columnList = "id", name = "scoreoffixture_id_hidx"),
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScoreOfFixture extends BaseEntity{
	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@SequenceGenerator(name = "scoreoffixture", sequenceName = "scoreoffixture.gen", initialValue = 1, allocationSize = 1)
	@Setter(AccessLevel.NONE)
	long id;
	
	@JsonProperty("home")
	@Column(name = "home")
	int home;
	
	@JsonProperty("away")
	@Column(name = "away")
	int away;
	
	@JsonProperty("timeofscore")
	@Column(name = "timeofscore")
	EnumForTimeOfScore timeOfScore;
}
