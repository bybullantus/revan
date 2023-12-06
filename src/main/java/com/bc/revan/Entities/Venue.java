package com.bc.revan.Entities;

import com.bc.revan.Entities.Base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="venues",indexes = {
		  @Index(columnList = "id", name = "venues_id_hidx"),
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Venue extends BaseEntity{

	@Id
	@JsonProperty("id")
	 long id;
	
	@JsonProperty("name")
	@Column(name = "name", length = 200)
	String name;
	
	@Nullable
	@JsonProperty("address")
	@Column(name = "address", length = 200)
	String address;
	
	@JsonProperty("city")
	@Nullable
	@Column(name = "city", length = 200)
	String city;
	
	@JsonProperty("capacity")
	@Nullable
	@Column(name = "capacity")
	int capacity;
	
	@JsonProperty("surface")
	@Nullable
	@Column(name = "surface", length = 200)
	String surface;
	
	@JsonProperty("image")
	@Column(name = "image", length = 200)
	String image;
	
}
