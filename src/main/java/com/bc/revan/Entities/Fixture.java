package com.bc.revan.Entities;

import com.bc.revan.Entities.Base.BaseEntity;
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
@Table(name = "fixtures",indexes = {
		   @Index(columnList = "id", name = "fixtures_id_hidx"),
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Fixture extends BaseEntity{

	@Id
	@Column(name = "id", nullable = false)
	long id;
	
	@JsonProperty("referee")
	@Column(name = "referee", length = 200)
	String referee;
	
	@JsonProperty("timezone")
	@Column(name = "timezone", length = 200)
	String timezone;
	
	@JsonProperty("date")
	@Column(name = "date", length = 200)
	String date;
	
	@JsonProperty("timestamp")
	@Column(name = "timestamp")
	long timestamp;
	
	@ManyToOne
	@JoinColumn(name = "venueid")
	Venue venue;
	
	@ManyToOne
	@JoinColumn(name = "periodsid")
	PeriodsOfFixture periods;
	
	@ManyToOne
	@JoinColumn(name = "statusid")
	StatusOfFixture status;
}
