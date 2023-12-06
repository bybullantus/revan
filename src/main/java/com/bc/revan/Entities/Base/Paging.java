package com.bc.revan.Entities.Base;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Paging {

	 @JsonProperty("current") 
	    int current;
	    @JsonProperty("total") 
	    int total;
}
