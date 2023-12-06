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
@Table(name = "seasons",indexes = {
		  @Index(columnList = "id", name = "seasons_id_hidx"),
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Season extends BaseEntity {

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@SequenceGenerator(name = "seasons_seq_gen", sequenceName = "seasons.gen", initialValue = 1, allocationSize = 1)
	@Setter(AccessLevel.NONE)
	long id;

	@JsonProperty("year")
	@Column(name = "year")
	int year;

	@JsonProperty("start")
	@Column(name = "start_date", length = 50)
	String start;

	@JsonProperty("end")
	@Column(name = "end_date", length = 50)
	String end;

	@JsonProperty("current")
	@Column(name = "current")
	boolean current;

	@ManyToOne
	@JoinColumn(name = "leagueid")
	League league;

}
