package com.bc.revan.Entities;

import java.util.List;

import com.bc.revan.Entities.Base.BaseEntity;
import com.bc.revan.Entities.Enums.EnumForForAndAgainst;
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
@Table(name = "streaksofbiggest",indexes = {
		  @Index(columnList = "id", name = "streaksofbiggest_id_hidx"),
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StreakOfBiggest extends BaseEntity{
	
	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@SequenceGenerator(name = "streaksofbiggest", sequenceName = "streaksofbiggest.gen", initialValue = 1, allocationSize = 1)
	@Setter(AccessLevel.PRIVATE)
	long id;
	
	@JsonProperty("wins")
	@Nullable
	@Column(name = "wins")
	int wins;
	
	@JsonProperty("loses")
	@Nullable
	@Column(name = "loses")
	int loses;
	
	@JsonProperty("draws")
	@Nullable
	@Column(name = "draws")
	int draws;
}
