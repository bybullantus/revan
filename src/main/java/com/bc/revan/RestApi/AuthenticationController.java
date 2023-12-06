package com.bc.revan.RestApi;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bc.revan.Business.IAuthenticationService;
import com.bc.revan.Entities.Dto.AuthRequestDto;
import com.bc.revan.Entities.Dto.AuthResponseDto;
import com.bc.revan.Entities.Dto.RegisterRequestDto;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@CrossOrigin
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name =  "Authentication")
public class AuthenticationController {
	@Autowired
	  private  IAuthenticationService service;

	 // @Hidden
	  @PostMapping("/register")
	  @PreAuthorize("hasAuthority('admin:create')")
	  public ResponseEntity<AuthResponseDto> register(
	      @RequestBody RegisterRequestDto request
	  ) {
		  
	    return ResponseEntity.ok(service.register(request));
	  }
	  @PostMapping("/authenticate")
	  public ResponseEntity<AuthResponseDto> authenticate(
	      @RequestBody AuthRequestDto request
	  ) {
	    return ResponseEntity.ok(service.authenticate(request));
	  }

	  @PostMapping("/refresh-token")
	  public void refreshToken(
	      HttpServletRequest request,
	      HttpServletResponse response
	  ) throws IOException {
	    service.refreshToken(request, response);
	  }

}
