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
@Table(name="startXIandsubsoflineup",indexes = {
		  @Index(columnList = "id", name = "startXIandsubsoflineup_id_hidx"),
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StartXIAndSubsOfLineup extends BaseEntity{
	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@SequenceGenerator(name = "startXIandsubsoflineup_seq_gen", sequenceName = "startXIandsubsoflineup.gen", initialValue = 1, allocationSize = 1)
	@Setter(AccessLevel.NONE)
	long id;
	
	@ManyToOne
	@JoinColumn(name = "playerid")
	Player player;
	
	@JsonProperty("position")
	@Nullable
	@Column(name = "position", length = 10)
	String position;
	
	@JsonProperty("number")
	@Nullable
	@Column(name = "number")
	int number;
	
	@JsonProperty("grid")
	@Nullable
	@Column(name = "grid", length = 10)
	String grid;
	
	
}
