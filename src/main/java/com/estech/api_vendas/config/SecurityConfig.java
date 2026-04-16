package com.estech.api_vendas.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import com.estech.api_vendas.security.JwtAuthFilter;
import com.estech.api_vendas.security.JwtService;

@Configuration
@Profile("local")
public class SecurityConfig {

  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity http, JwtService jwtService) throws Exception {
    http
        .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/auth/**").permitAll()
            .anyRequest().authenticated());

    http.addFilterBefore(
        new JwtAuthFilter(jwtService),
        org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.class);

    return http.build();
  }
}
