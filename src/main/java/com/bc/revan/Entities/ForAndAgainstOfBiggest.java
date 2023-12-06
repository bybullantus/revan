package com.bc.revan.Entities;

import java.util.List;

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

@Table(name = "forandagainstofbiggest",indexes = {
		  @Index(columnList = "id", name = "forandagainstofbiggest_id_hidx"),
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class ForAndAgainstOfBiggest extends BaseEntity{
	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@SequenceGenerator(name = "forandagainstofbiggest", sequenceName = "forandagainstofbiggest.gen", initialValue = 1, allocationSize = 1)
	@Setter(AccessLevel.NONE)
	long id;
	
	@JsonProperty("home")
	@Nullable
	@Column(name = "home", length = 20)
	String home;
	
	@JsonProperty("away")
	@Nullable
	@Column(name = "away", length = 20)
	String away;
	
	@JsonProperty("forandagainst")
	@Column(name = "forandagainst")
	EnumForForAndAgainst forAndAgainst;
}
