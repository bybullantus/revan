package com.bc.revan.Entities;

import java.util.List;

import com.bc.revan.Entities.Base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.annotation.Nullable;
import jakarta.persistence.Cacheable;
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

@Entity
@Table(name = "leagues",indexes = {
		   @Index(columnList = "id", name = "leagues_id_hidx"),
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class League extends BaseEntity {

	@Id
	@JsonProperty("id")
	long id;

	@JsonProperty("name")
	@Column(name = "name", length = 200)
	String name;

	@JsonProperty("type")
	@Column(name = "type", length = 20)
	String type;

	@JsonProperty("logo")
	@Column(name = "logo", length = 200)
	String logo;

	@ManyToOne
	@JoinColumn(name = "countryid")
	Country country;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "leagueid")
	List<Season> seasons;
	

}
