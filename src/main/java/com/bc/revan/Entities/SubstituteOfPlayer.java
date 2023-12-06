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
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "substituteofplayers", indexes = { @Index(columnList = "id", name = "substituteofplayers_id_hidx"), })
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubstituteOfPlayer extends BaseEntity {
	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@SequenceGenerator(name = "substituteofplayers_seq_gen", sequenceName = "substituteofplayers.gen", initialValue = 1, allocationSize = 1)
	@Setter(AccessLevel.NONE)
	long id;
	
	@JsonProperty("in")
	@Nullable
	@Column(name = "inofsub", columnDefinition = "int default 0") 
	int in;
	
	@JsonProperty("out")
	@Nullable
	@Column(name = "out", columnDefinition = "int default 0") 
	int out;
	
	@JsonProperty("bench")
	@Nullable
	@Column(name = "bench", columnDefinition = "int default 0") 
	int bench;
}
