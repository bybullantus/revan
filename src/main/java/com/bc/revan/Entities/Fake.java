package com.bc.revan.Entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Fake{

	@JsonProperty("oran")
	@Nullable
	String oran;
	
	@JsonProperty("maclar")
	List<FakeMatches> maclar;
}
