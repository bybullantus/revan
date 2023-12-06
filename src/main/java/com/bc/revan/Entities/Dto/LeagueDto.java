package com.bc.revan.Entities.Dto;

import java.io.Serializable;
import java.util.List;


import jakarta.persistence.Cacheable;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public class LeagueDto implements Serializable {
	
	long id;
	String name;	
	String type;	
	String logo;
	
	CountryDto country;
	List<SeasonDto> seasons;
   
}
