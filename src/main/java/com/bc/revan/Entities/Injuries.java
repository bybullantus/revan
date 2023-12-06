package com.bc.revan.Entities;

import java.util.List;

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

@Entity
@Table(name = "injuries",indexes = {
		   @Index(columnList = "id", name = "injuries_id_hidx"),
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Injuries  extends BaseEntity{
	
	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@SequenceGenerator(name = "injuries", sequenceName = "injuries.gen", initialValue = 1, allocationSize = 1)
	@Setter(AccessLevel.NONE)
	long id;
	
	@ManyToOne
	@JoinColumn(name = "playerid")
	Player player;
	
	@ManyToOne
	@JoinColumn(name = "teamid")
	Team team;
	
	@ManyToOne
	@JoinColumn(name = "fixtureid")
	Fixture fixture;
	
	@ManyToOne
	@JoinColumn(name = "leagueid")
	League league;
	
	@ManyToOne
	@JoinColumn(name = "seasonid")
	Season season;
	
	@JsonProperty("type")
	@Nullable
	@Column(name = "type", length = 100)
	String type;
	
	@JsonProperty("reason")
	@Nullable
	@Column(name = "reason", length = 100)
	String reason;
}
