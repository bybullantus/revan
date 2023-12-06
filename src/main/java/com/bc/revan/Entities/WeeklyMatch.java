package com.bc.revan.Entities;

import java.sql.Date;

import com.bc.revan.Entities.Base.BaseEntity;
import com.bc.revan.Entities.Enums.EnumForBetType;
import com.bc.revan.Entities.Enums.EnumForStatus;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.micrometer.common.lang.Nullable;
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



@Table(name = "weeklymatches",indexes = {
		  @Index(columnList = "id", name = "weeklymatches_id_hidx"),
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class WeeklyMatch extends BaseEntity{

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@SequenceGenerator(name = "weeklymatches_seq_gen", sequenceName = "weeklymatches.gen", initialValue = 1, allocationSize = 1)
	@Setter(AccessLevel.NONE)
	long id;
	
	@ManyToOne
	@JoinColumn(name = "fixturemainnodeid")
	FixtureMainNode fixtureMainNode;
	
	@JsonProperty("bettype")
	@Column(name = "bettype", length = 100)
	EnumForBetType betType;
	
	@JsonProperty("status")
	@Column(name = "status" , length = 100)
	EnumForStatus status=EnumForStatus.NULL;
	
	@JsonProperty("createddate")
	@Column(name = "createddate", length = 100)
	Date createdDate;
	
	@JsonProperty("weeknumber")
	@Column(name = "weeknumber")
	@Nullable
	long weekNumber;

}
