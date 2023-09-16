package com.tutorial.springsecurity.demosecurity.securityConfig;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

	@Bean
	public UserDetailsManager userDetailsManager(DataSource dataSource) {
		JdbcUserDetailsManager jdbc = new JdbcUserDetailsManager(dataSource);
		jdbc.setUsersByUsernameQuery("select username, password, active from users where username = ?");
		jdbc.setAuthoritiesByUsernameQuery("select username, role from roles where username=?");
		return jdbc;
	}

		
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(config -> 
				config
					.requestMatchers(HttpMethod.GET, "/employees/list").hasAnyRole("USER", "ADMIN")
					.requestMatchers(HttpMethod.GET, "/employees/delete/**").hasRole("ADMIN")
					.requestMatchers(HttpMethod.POST, "/employees/FormForUpdate").hasRole("ADMIN")
					.requestMatchers(HttpMethod.GET, "/employees/FormForAdd").hasRole("ADMIN")
					.requestMatchers(HttpMethod.GET, "/employees/ShowDetails/**").hasAnyRole("USER", "ADMIN")
					.anyRequest().authenticated())
			.formLogin(form -> form.loginPage("/loginPage").loginProcessingUrl("/authenticateTheUser").permitAll())
			.logout(logout -> logout.permitAll())
			.exceptionHandling(config -> 
									config.accessDeniedPage("/accessDenied"));
		http.httpBasic(Customizer.withDefaults());
		return http.build();
	}

	
	
}
