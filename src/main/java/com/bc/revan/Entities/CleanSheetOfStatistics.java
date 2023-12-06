package com.bc.revan.Entities;

import java.math.BigDecimal;
import java.util.List;

import com.bc.revan.Entities.Base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "cleansheetofstatistics",indexes = {
		  @Index(columnList = "id", name = "cleansheetofstatistics_id_hidx"),
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class CleanSheetOfStatistics extends BaseEntity{

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@SequenceGenerator(name = "cleansheetofstatistics", sequenceName = "cleansheetofstatistics.gen", initialValue = 1, allocationSize = 1)
	@Setter(AccessLevel.NONE)
	long id;
	
	@JsonProperty("home")
	@Column(name = "home" , precision = 5, scale = 2)
	int home;
	
	@JsonProperty("away")
	@Column(name = "away" , precision = 5, scale = 2)
	int away;
	
	@JsonProperty("total")
    @Column(name = "total", precision = 5, scale = 2)
	int total;
	
	
}
