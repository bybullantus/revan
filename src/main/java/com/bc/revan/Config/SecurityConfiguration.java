package com.bc.revan.Config;

import static com.bc.revan.Entities.Enums.Permission.ADMIN_CREATE;
import static com.bc.revan.Entities.Enums.Permission.ADMIN_DELETE;
import static com.bc.revan.Entities.Enums.Permission.ADMIN_READ;
import static com.bc.revan.Entities.Enums.Permission.ADMIN_UPDATE;
import static com.bc.revan.Entities.Enums.Permission.MANAGER_CREATE;
import static com.bc.revan.Entities.Enums.Permission.MANAGER_DELETE;
import static com.bc.revan.Entities.Enums.Permission.MANAGER_READ;
import static com.bc.revan.Entities.Enums.Permission.MANAGER_UPDATE;
import static com.bc.revan.Entities.Enums.Role.ADMIN;
import static com.bc.revan.Entities.Enums.Role.MANAGER;
import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.PUT;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfiguration {
	private final JwtAuthenticationFilter jwtAuthFilter;
	private final AuthenticationProvider authenticationProvider;
	private final LogoutHandler logoutHandler;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.exceptionHandling().authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)).and()
				.csrf().disable().cors().and().authorizeHttpRequests()
				.requestMatchers(new OrRequestMatcher(new AntPathRequestMatcher("/api/auth/**"),
						new AntPathRequestMatcher("/v2/api-docs"), new AntPathRequestMatcher("/v3/api-docs"),
						new AntPathRequestMatcher("/v3/api-docs/**"), new AntPathRequestMatcher("/swagger-resources"),
						new AntPathRequestMatcher("/swagger-resources/**"),
						new AntPathRequestMatcher("/configuration/ui"),
						new AntPathRequestMatcher("/configuration/security"),
						new AntPathRequestMatcher("/swagger-ui/**"), new AntPathRequestMatcher("/webjars/**"),
						new AntPathRequestMatcher("/swagger-ui.html")),
						new AntPathRequestMatcher("/api/fakeMatches/**"),
						new AntPathRequestMatcher("/api/weeklyMatches/**"),
						//new AntPathRequestMatcher("/tutorial/**"),
						new AntPathRequestMatcher("/live/**"))

				.permitAll()

				.requestMatchers(new AntPathRequestMatcher("/api/**")).hasAnyRole(ADMIN.name(), MANAGER.name()) // hepsi
				.anyRequest().authenticated().and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				.authenticationProvider(authenticationProvider)
				.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class).logout()
				.logoutUrl("/api/auth/logout").addLogoutHandler(logoutHandler)
				.logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext());

		return http.build();
		  
	}

}
