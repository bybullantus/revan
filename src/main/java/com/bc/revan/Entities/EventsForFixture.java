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
@Table(name = "eventsforfixture",indexes = {
		   @Index(columnList = "id", name = "eventsforfixture_id_hidx"),
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventsForFixture extends BaseEntity{

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@SequenceGenerator(name = "eventsforfixture", sequenceName = "eventsforfixture.gen", initialValue = 1, allocationSize = 1)
	@Setter(AccessLevel.NONE)
	long id;
	
	@JsonProperty("type")
	@Nullable
	@Column(name = "type", length = 100)
	String type;
	
	@JsonProperty("detail")
	@Nullable
	@Column(name = "detail", length = 100)
	String detail;
	
	@JsonProperty("comments")
	@Nullable
	@Column(name = "comments", length = 100)
	String comments;
	
	
	@ManyToOne
	@Nullable
	@JoinColumn(name = "timeid")
	TimeOfEvent time;
	
	
	@ManyToOne
	@Nullable
	@JoinColumn(name = "teamid")
	Team team;
	
	@ManyToOne
	@Nullable
	@JoinColumn(name = "playerid")
	Player player;
	
	@ManyToOne
	@Nullable
	@JoinColumn(name = "assistid")
	Player assist;
	
	@ManyToOne
	@JoinColumn(name = "fixtureid")
	Fixture fixture;
}
