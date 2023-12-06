package com.bc.revan.Entities;

import java.util.List;

import com.bc.revan.Entities.Base.BaseEntity;
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
import jakarta.persistence.Version;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name="coachs",indexes = {
		  @Index(columnList = "id", name = "coachs_id_hidx"),
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Coachs extends BaseEntity{

	@Id
	@Column(name = "id", nullable = false)
	long id;
	
	@JsonProperty("name")
	@Nullable
	@Column(name = "name", length = 200)
	String name;
	
	@JsonProperty("firstname")
	@Nullable
	@Column(name = "firstname", length = 150)
	String firstName;
	
	@JsonProperty("lastname")
	@Nullable
	@Column(name = "lastname", length = 150)
	String lastName;
	
	@ManyToOne
	@JoinColumn(name = "birthid")
	Birth birth;
	
	@JsonProperty("nationality")
	@Nullable
	@Column(name = "nationality", length = 150)
	String nationality;
	
	@JsonProperty("height")
	@Nullable
	@Column(name = "height", length = 150)
	String height;
	
	@JsonProperty("weight")
	@Nullable
	@Column(name = "weight", length = 150)
	String weight;
	
	@JsonProperty("photo")
	@Nullable
	@Column(name = "photo", length = 200)
	String photo;
	
	@ManyToOne
	@JoinColumn(name = "teamid")
	Team team;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "coachsid")
	List<CareerOfCoach> careers;
	
}
