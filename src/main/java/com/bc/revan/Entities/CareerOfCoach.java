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

@Table(name = "carrerofcoach",indexes = {
		  @Index(columnList = "id", name = "carrerofcoach_id_hidx"),
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class CareerOfCoach extends BaseEntity{
	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@SequenceGenerator(name = "carrerofcoach_seq_gen", sequenceName = "carrerofcoach.gen", initialValue = 1, allocationSize = 1)
	@Setter(AccessLevel.NONE)
	long id;
	
	@ManyToOne
	@Nullable
	@JoinColumn(name = "teamid")
	Team team;
	
	@JsonProperty("start")
	@Nullable
	@Column(name = "startOfCareer", length = 20)
	String start;
	
	@JsonProperty("end")
	@Nullable
	@Column(name = "endOfCareer", length = 20)
	String end;
	
	
}
