package com.bc.revan.Entities;

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
@Table(name = "statusoffixture",indexes = {
		  @Index(columnList = "id", name = "statusoffixture_id_hidx"),
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StatusOfFixture extends BaseEntity{

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@SequenceGenerator(name = "statusoffixture", sequenceName = "statusoffixture.gen", initialValue = 1, allocationSize = 1)
	@Setter(AccessLevel.NONE)
	long id;
	
	@JsonProperty("elapsed")
	@Column(name = "elapsed")
	int elapsed;
	
	@JsonProperty("short")
	@Column(name = "short", length = 200)
	String shortOfStatus;
	
	@JsonProperty("long")
	@Column(name = "long", length = 50)
	String longOfStatus;
	
}
