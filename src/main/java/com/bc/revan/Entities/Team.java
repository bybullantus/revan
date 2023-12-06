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
@Table(name="teams",indexes = {
		  @Index(columnList = "id", name = "teams_id_hidx"),
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Team extends BaseEntity{

	@Id
	@JsonProperty("id")
	 long id;
	
	@JsonProperty("name")
	@Column(name = "name", length = 200)
	String name;
	
	@Nullable
	@JsonProperty("code")
	@Column(name = "code", length = 6)
	String code;
	
	@JsonProperty("country")
	@Nullable
	@Column(name = "country", length = 200)
	String country;
	
	@JsonProperty("founded")
	@Nullable
	@Column(name = "founded")
	int founded;
	
	@JsonProperty("national")
	@Nullable
	@Column(name = "national")
	boolean national;
	
	@JsonProperty("logo")
	@Nullable
	@Column(name = "logo", length = 200)
	String logo;
	
	@ManyToOne
	@Nullable
	@JoinColumn(name = "venueid")
	Venue venue;
	
}
