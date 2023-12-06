package com.bc.revan.Entities;

import com.bc.revan.Entities.Base.BaseEntity;
import com.bc.revan.Entities.Enums.EnumForForAndAgainst;
import com.bc.revan.Entities.Enums.EnumForWinsAndLoses;
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

@Table(name = "winsandlosesofbiggest",indexes = {
		  @Index(columnList = "id", name = "winsandlosesofbiggest_id_hidx"),
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class WinsAndLosesOfBiggest extends BaseEntity{

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@SequenceGenerator(name = "winsandlosesofbiggest", sequenceName = "winsandlosesofbiggest.gen", initialValue = 1, allocationSize = 1)
	@Setter(AccessLevel.PRIVATE)
	long id;
	
	@JsonProperty("home")
	@Nullable
	@Column(name = "home", length = 20)
	String home;
	
	@JsonProperty("away")
	@Nullable
	@Column(name = "away", length = 20)
	String away;
	
	@JsonProperty("winsandloses")
	@Column(name = "winsandloses")
	EnumForWinsAndLoses winsAndLoses;
}
