package com.bc.revan.Business;

import java.io.IOException;

import com.bc.revan.Business.Base.IBaseService;
import com.bc.revan.Entities.User;
import com.bc.revan.Entities.Dto.AuthRequestDto;
import com.bc.revan.Entities.Dto.AuthResponseDto;
import com.bc.revan.Entities.Dto.RegisterRequestDto;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface IAuthenticationService extends IBaseService<User>{
	public AuthResponseDto register(RegisterRequestDto request) ;
	
	public AuthResponseDto authenticate(AuthRequestDto request);
	public void refreshToken(
	          HttpServletRequest request,
	          HttpServletResponse response
	  ) throws IOException;
	
	public boolean getByEmail(String Email);
}
