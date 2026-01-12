package com.example.spring_oauth2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.spring_oauth2.security.CustomAuthenticationEntryPoint;
import com.example.spring_oauth2.security.JwtFilter;
import com.example.spring_oauth2.security.JwtUtils;

@Configuration
public class SecurityConfig {

    private JwtUtils jwtUtils;
    private JwtFilter jwtFilter;

    public SecurityConfig(JwtFilter jwtFilter, JwtUtils jwtUtils) {
        this.jwtFilter = jwtFilter;
        this.jwtUtils = jwtUtils;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
            .csrf(csrf -> csrf.disable())
            // only used with frontend or when app is an api
            // .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/login/**").permitAll()
                .anyRequest().authenticated()
            )
            .exceptionHandling(e -> e.authenticationEntryPoint(new CustomAuthenticationEntryPoint()))
            .oauth2Login(oauth -> oauth
                .successHandler((request, response, authentaication) -> {
                    String token = jwtUtils.generateToken(authentaication.getName());
                    response.sendRedirect("/dashboard?token=" + token);
                })    
            );

        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
