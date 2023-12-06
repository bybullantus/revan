package com.bc.revan.Entities;

import java.util.List;

import com.bc.revan.Entities.Base.BaseEntity;
import com.bc.revan.Entities.Enums.EnumForPWDL;
import com.bc.revan.Entities.Enums.EnumForTimeInterval;
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
@Table(name = "timeintervals",indexes = {
		   @Index(columnList = "id", name = "timeintervals_id_hidx"),
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TimeInterval extends BaseEntity {

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@SequenceGenerator(name = "timeintervals", sequenceName = "timeintervals.gen", initialValue = 1, allocationSize = 1)
	@Setter(AccessLevel.NONE)
	long id;
	
	@JsonProperty("total")
	@Column(name = "total")
	int total;
	
	@JsonProperty("percentage")
	@Column(name = "percentage", length = 20)
	String percentage;
	
	
	@JsonProperty("timeinterval")
	@Column(name = "timeinterval")
	EnumForTimeInterval timeInterval;
}
