package com.security.OAuth2;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.util.Date;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Disable CSRF for stateless JWT APIs
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login/**", "/error").permitAll()
                        .anyRequest().authenticated()
                )
                .oauth2Login(oauth2 -> oauth2
                        .successHandler(oidcSuccessHandler()) // Custom handler to generate JWT
                );

        return http.build();
    }

    @Bean
    public AuthenticationSuccessHandler oidcSuccessHandler() {
        return (request, response, authentication) -> {
            OidcUser oidcUser = (OidcUser) authentication.getPrincipal();

            // 1. Logic to create/update user in your DB can go here

            // 2. Generate your internal Custom JWT
            String jwt = JwtUtils.generateToken(oidcUser);

            // 3. Redirect back to your Frontend (React/Angular) with the token
            // In production, consider using an HttpOnly Cookie instead of a URL fragment
            response.sendRedirect("http://localhost:3000/callback#token=" + jwt);
        };
    }
}