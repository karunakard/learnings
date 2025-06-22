package com.karantech.java.product_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.karantech.java.product_service.service.UserInfouserDetailsService;

@Configuration
@EnableWebSecurity
//@EnableMethodSecurity
public class SecurityConfig {
	//first one is the hardcoded user name and password and roles

	/*
	 * @Bean public UserDetailsService userDetailsService(PasswordEncoder encoder) {
	 * UserDetails userDetails =
	 * User.withUsername("karan").password(encoder.encode("pwd1")).roles("ADMIN").
	 * build();
	 * 
	 * UserDetails userDetails2 =
	 * User.withUsername("aman").password(encoder.encode("pwd2")).roles("USER").
	 * build();
	 * 
	 * return new InMemoryUserDetailsManager(userDetails, userDetails2);
	 * 
	 * }
	 */
	//this one is the own created userDetaislService by mapping the user from db to the userDetais obj
	@Bean
	public UserDetailsService userDetailsService() {
		
		return new UserInfouserDetailsService();

	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(customizer -> customizer.disable())
				.authorizeHttpRequests((auth) -> auth.requestMatchers("/product/welcome","/product/add").permitAll()
						.requestMatchers("/product/all").hasRole("ADMIN")
						.requestMatchers("/product/byid/{id}/**").hasRole("USER")
						.anyRequest()
						.fullyAuthenticated())
				.httpBasic(Customizer.withDefaults())
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {

		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider(userDetailsService());
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}

}
