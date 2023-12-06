package com.bc.revan.Entities.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponseDto {
	@JsonProperty("access_token")
	String accessToken;
	@JsonProperty("refresh_token")
	String refreshToken;
}
