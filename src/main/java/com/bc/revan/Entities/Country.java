package com.bc.revan.Entities;



import com.bc.revan.Entities.Base.BaseEntity;
import com.bc.revan.Entities.Enums.EnumForPWDL;
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
@Table(name="countries",indexes = {
		  @Index(columnList = "id", name = "countries_id_hidx"),
})

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Country extends BaseEntity{
	
	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@SequenceGenerator(name = "countries_seq_gen", sequenceName = "countries.gen", initialValue = 1, allocationSize = 1)
	@Setter(AccessLevel.NONE)
	 long id;
	
	@JsonProperty("name")
	@Nullable
	@Column(name = "name", length = 20)
	String name;
	
	
	@JsonProperty("code")
	@Nullable
	@Column(name = "code", length = 5)
	String code;
	
	@JsonProperty("flag")
	@Nullable
	@Column(name = "flag", length = 200)
	String flag;
}
