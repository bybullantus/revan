package com.bc.revan.Entities;

import java.util.List;

import com.bc.revan.Entities.Base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.annotation.Nullable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "players",indexes = {
		  @Index(columnList = "id", name = "players_id_hidx"),
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Player extends BaseEntity{
	
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
	@Nullable
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
	
	@JsonProperty("injured")
	@Nullable
	@Column(name = "injured")
	Boolean injured;
	
	@JsonProperty("photo")
	@Nullable
	@Column(name = "photo", length = 200)
	String photo;
	
	@JsonProperty("number")
	@Nullable
	@Column(name = "number", columnDefinition = "int default 0") 
	int number;
	
	@JsonProperty("position")
	@Nullable
	@Column(name = "position", length = 20)
	String position;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "playerid")
	@Nullable
	List<StatisticsOfPlayer> statistics;
	
}
