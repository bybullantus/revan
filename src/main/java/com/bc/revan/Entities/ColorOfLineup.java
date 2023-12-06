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
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="coloroflineup",indexes = {
		  @Index(columnList = "id", name = "coloroflineup_id_hidx"),
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ColorOfLineup extends BaseEntity{
	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@SequenceGenerator(name = "coloroflineup_seq_gen", sequenceName = "coloroflineup.gen", initialValue = 1, allocationSize = 1)
	@Setter(AccessLevel.NONE)
	long id;
	
	@JsonProperty("primary")
	@Column(name = "primaryoflineup", length = 10)
	String primary;
	
	@JsonProperty("number")
	@Column(name = "numberoflineup", length = 10)
	String number;
	
	@JsonProperty("border")
	@Column(name = "borderoflineup", length = 10)
	String border;

}
