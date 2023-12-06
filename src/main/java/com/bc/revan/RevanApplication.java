package com.bc.revan;

import static com.bc.revan.Entities.Enums.Role.ADMIN;
import static com.bc.revan.Entities.Enums.Role.MANAGER;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.bc.revan.Business.IAuthenticationService;
import com.bc.revan.Entities.Dto.RegisterRequestDto;


@EnableAspectJAutoProxy
@EnableScheduling
@SpringBootApplication
@ComponentScan
public class RevanApplication {

	public static void main(String[] args) {
		SpringApplication.run(RevanApplication.class, args);
	}
	
	public CommandLineRunner commandLineRunner(
			@Autowired
			IAuthenticationService service
	) {
		return args -> {			
			
			var admin = RegisterRequestDto.builder()
					.firstname("Admin")
					.lastname("Admin")
					.email("admin@mail.com")
					.password("password")
					.role(ADMIN)
					.build();
			if(!service.getByEmail(admin.getEmail())) 	
			System.out.println("Admin token: " + service.register(admin).getAccessToken());

			var manager = RegisterRequestDto.builder()
					.firstname("Admin")
					.lastname("Admin")
					.email("manager@mail.com")
					.password("password")
					.role(MANAGER)
					.build();
			if(!service.getByEmail(manager.getEmail())) 					
			System.out.println("Manager token: " + service.register(manager).getAccessToken());

		};
	}

}
