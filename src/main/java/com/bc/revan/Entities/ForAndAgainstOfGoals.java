package com.bc.revan.Entities;

import java.util.List;

import com.bc.revan.Entities.Base.BaseEntity;
import com.bc.revan.Entities.Enums.EnumForForAndAgainst;
import com.bc.revan.Entities.Enums.EnumForPWDL;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.annotation.Nullable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "forandagainstofgoals",indexes = {
		  @Index(columnList = "id", name = "forandagainstofgoals_id_hidx"),
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ForAndAgainstOfGoals extends BaseEntity{

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@SequenceGenerator(name = "forandagainstofgoals", sequenceName = "forandagainstofgoals.gen", initialValue = 1, allocationSize = 1)
	@Setter(AccessLevel.NONE)
	long id;
	

	@JsonProperty("forandagainst")
	@Column(name = "forandagainst")
	EnumForForAndAgainst ForAndAgainst;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "forandagainstofgoalsid")
	List<TotalAndAverage> totalAndAverage;


	@ManyToOne
	@JoinColumn(name = "minuteid")
	Minute minute;
}
