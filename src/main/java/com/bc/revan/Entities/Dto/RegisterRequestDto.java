package com.bc.revan.Entities.Dto;

import com.bc.revan.Entities.Enums.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestDto {
	String firstname;
	String lastname;
	String email;
	String password;
	Role role;
}
