package com.realtimeweb.partyapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/ws/**", "/app/**", "/topic/**").permitAll() // WebSocket végpontok engedélyezése
                        .anyRequest().authenticated()
                )
                .csrf(csrf -> csrf.disable()) // CSRF védelem kikapcsolása
                .httpBasic(); // Alapvető hitelesítés más végpontokhoz

        return http.build();
    }
}



