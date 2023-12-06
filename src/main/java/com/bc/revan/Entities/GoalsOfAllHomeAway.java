package com.bc.revan.Entities;

import com.bc.revan.Entities.Base.BaseEntity;
import com.bc.revan.Entities.Enums.EnumForAllHomeAway;
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
@Table(name = "goalsofallhomeaway",indexes = {
		  @Index(columnList = "id", name = "goalsofallhomeaway_id_hidx"),
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GoalsOfAllHomeAway extends BaseEntity{

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@SequenceGenerator(name = "goalsofallhomeaway", sequenceName = "goalsofallhomeaway.gen", initialValue = 1, allocationSize = 1)
	@Setter(AccessLevel.NONE)
	long id;
	
	@JsonProperty("for")
	@Column(name = "forofgoals")
	int forofgoals;
	
	@JsonProperty("against")
	@Column(name = "against")
	int against;
	
}
