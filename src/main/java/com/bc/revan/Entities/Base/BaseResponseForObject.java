package com.bc.revan.Entities.Base;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class BaseResponseForObject<T> {
	
	@JsonProperty("get")
	String get;

/*	@JsonProperty("parameters")
	ArrayList<Object> parameters;
*/
	@JsonProperty("errors")
	ArrayList<Object> errors;

	@JsonProperty("results")
	int results;

	@JsonProperty("paging")
	Paging paging;

	@JsonProperty("response")
	T responsObject;
	
	
}