package com.bc.revan.Entegration.Football;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

public abstract class BaseRequest {

	@Value("${football.apiurl}")
	 String apiUrl;

	@Value("${football.user}")
	 String user;

	@Value("${football.password}")
	 String password;
	
	  RestTemplate restTemplate;
}
