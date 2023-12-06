package com.bc.revan.Entities;

import java.util.List;

import com.bc.revan.Entities.Base.BaseEntity;
import com.bc.revan.Entities.Enums.EnumForAllHomeAway;
import com.bc.revan.Entities.Enums.EnumForScoredAndMissed;
import com.fasterxml.jackson.annotation.JsonProperty;

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

@Entity
@Table(name = "allhomeawayofstandingdetail",indexes = {
		  @Index(columnList = "id", name = "allhomeawayofstandingdetail_id_hidx"),
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class AllHomeAwayOfStandingDetail extends BaseEntity{
	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@SequenceGenerator(name = "allhomeawayofstandingdetail", sequenceName = "allhomeawayofstandingdetail.gen", initialValue = 1, allocationSize = 1)
	@Setter(AccessLevel.NONE)
	long id;
	
	@JsonProperty("played")
	@Column(name = "played")
	int played;
	
	@JsonProperty("win")
	@Column(name = "win")
	int win;
	
	@JsonProperty("draw")
	@Column(name = "draw")
	int draw;
	
	@JsonProperty("lose")
	@Column(name = "lose")
	int lose;
	
	@ManyToOne
	@JoinColumn(name = "goalsofallhomeawayid")
	GoalsOfAllHomeAway goalsOfAllHomeAway;
	
	
	@JsonProperty("allhomeaway")
	@Column(name = "allhomeaway")
	EnumForAllHomeAway allHomeAway;

}
