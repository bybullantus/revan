package com.bc.revan.Business.Impl;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bc.revan.Business.IAuthenticationService;
import com.bc.revan.Business.Base.GenericManager;
import com.bc.revan.Config.JwtService;
import com.bc.revan.DataAccess.ITokenDal;
import com.bc.revan.DataAccess.IUserDal;
import com.bc.revan.Entities.Token;
import com.bc.revan.Entities.User;
import com.bc.revan.Entities.Dto.AuthRequestDto;
import com.bc.revan.Entities.Dto.AuthResponseDto;
import com.bc.revan.Entities.Dto.RegisterRequestDto;
import com.bc.revan.Entities.Enums.TokenType;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Service
@AllArgsConstructor
public class AuthenticatManager extends GenericManager<User, IUserDal> implements IAuthenticationService {
	@Autowired
	  private  ITokenDal tokenDal;
	 
	 
	  private final PasswordEncoder passwordEncoder;
	  private final JwtService jwtService;
	  
	  @Autowired
	  private  AuthenticationManager authenticationManager;

	  @Override
	  public AuthResponseDto register(RegisterRequestDto request) {
	    var user = User.builder()
	        .firstname(request.getFirstname())
	        .lastname(request.getLastname())
	        .email(request.getEmail())
	        .password(passwordEncoder.encode(request.getPassword()))
	        .role(request.getRole())
	        .build();
	    var savedUser = genericDal.add(user);
	    var jwtToken = jwtService.generateToken(user);
	    var refreshToken = jwtService.generateRefreshToken(user);
	    saveUserToken(savedUser, jwtToken);
	    return AuthResponseDto.builder()
	        .accessToken(jwtToken)
	            .refreshToken(refreshToken)
	        .build();
	  }

	  @Override
	  public AuthResponseDto authenticate(AuthRequestDto request) {
	    authenticationManager.authenticate(
	        new UsernamePasswordAuthenticationToken(
	            request.getEmail(),
	            request.getPassword()
	        )
	    );
	    var user = genericDal.getByEmail(request.getEmail())
	        .orElseThrow();
	    var jwtToken = jwtService.generateToken(user);
	    var refreshToken = jwtService.generateRefreshToken(user);
	    revokeAllUserTokens(user);
	    saveUserToken(user, jwtToken);
	    return AuthResponseDto.builder()
	        .accessToken(jwtToken)
	            .refreshToken(refreshToken)
	        .build();
	  }


	  private void saveUserToken(User user, String jwtToken) {
	    var token = Token.builder()
	        .user(user)
	        .token(jwtToken)
	        .tokenType(TokenType.BEARER)
	        .expired(false)
	        .revoked(false)
	        .build();
	    tokenDal.add(token);
	  }

	  private void revokeAllUserTokens(User user) {
	    var validUserTokens = tokenDal.getTokenbyUserId(user.getId());
	    if (validUserTokens.isEmpty())
	      return;
	    validUserTokens.forEach(token -> {
	      token.setExpired(true);
	      token.setRevoked(true);
	    });
	    tokenDal.addAll(validUserTokens);
	  }

	  @Override
	  public void refreshToken(
	          HttpServletRequest request,
	          HttpServletResponse response
	  ) throws IOException {
	    final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
	    final String refreshToken;
	    final String userEmail;
	    if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
	      return;
	    }
	    refreshToken = authHeader.substring(7);
	    userEmail = jwtService.extractUsername(refreshToken);
	    if (userEmail != null) {
	      var user = genericDal.getByEmail(userEmail)
	              .orElseThrow();
	      if (jwtService.isTokenValid(refreshToken, user)) {
	        var accessToken = jwtService.generateToken(user);
	        revokeAllUserTokens(user);
	        saveUserToken(user, accessToken);
	        var authResponse = AuthResponseDto.builder()
	                .accessToken(accessToken)
	                .refreshToken(refreshToken)
	                .build();
	        new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
	      }
	    }
	  }

	@Override
	public boolean getByEmail(String Email) {
		return  tokenDal.getByEmail(Email);
	}
}
