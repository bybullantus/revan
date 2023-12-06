package com.bc.revan.Entities;

import java.util.Date;

import com.bc.revan.Entities.Base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "births",indexes = {
		  @Index(columnList = "id", name = "births_id_hidx"),
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Birth extends BaseEntity{
	
	
	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@SequenceGenerator(name = "births_seq_gen", sequenceName = "births.gen", initialValue = 1, allocationSize = 1)
	long id;
	
	@JsonProperty("date")
	@Nullable
	@Column(name = "date", length = 200)
	Date date;
	
	@JsonProperty("place")
	@Nullable
	@Column(name = "place", length = 150)
	String place;
	
	@JsonProperty("country")
	@Nullable
	@Column(name = "country", length = 150)
	String country;
}
