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

@Table(name = "fakematches",indexes = {
		  @Index(columnList = "id", name = "fakematches_id_hidx"),
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class FakeMatches {
	
	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@SequenceGenerator(name = "fakematches_seq_gen", sequenceName = "fakematches.gen", initialValue = 1, allocationSize = 1)
	@Setter(AccessLevel.NONE)
	long id;
	
	@JsonProperty("ev")
	@Nullable
	@Column(name = "ev", length = 70)
	String ev;
	
	@JsonProperty("deplasman")
	@Nullable
	@Column(name = "deplasman", length = 70)
	String deplasman;
	
	@JsonProperty("tarih")
	@Nullable
	@Column(name = "tarih", length = 70)
	String tarih;
	
	@JsonProperty("bahis")
	@Nullable
	@Column(name = "bahis", length = 50)
	String bahis;
	
	@JsonProperty("hafta")
	@Nullable
	@Column(name = "hafta")
	int hafta;
	
	@JsonProperty("statu")
	@Nullable
	@Column(name = "statu")
	Boolean statu;
	
	@JsonProperty("risk")
	@Nullable
	@Column(name = "risk")
	Boolean risk = false;
	
}
