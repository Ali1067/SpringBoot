package com.example.Auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    DataSource dataSource;
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(a ->
                a.requestMatchers("/h2-console/**").permitAll()
                .anyRequest().authenticated());    //Authenticate every incoming request
//                http.sessionManagement(sessionManagement ->sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//        http.formLogin(Customizer.withDefaults());          //show default form to Log in - username is user and password from console

        http.httpBasic(Customizer.withDefaults());          // basic = username + password converted to base63 like username:password
          http.csrf(AbstractHttpConfigurer::disable) ;
            http.headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable));
        return http.build();                                //build the http
    }

    @Bean
    UserDetailsService userDetailsService() {
        UserDetails user1    = User.withUsername("Ali1067@outlook.com")
                .password(passwordEncoder().encode("+923312611993"))
                .roles("ADMIN")
                .build();

        UserDetails user2    = User.withUsername("user")
                .password(passwordEncoder().encode("user"))
                .roles("USER")
                .build();

//        return new InMemoryUserDetailsManager(user1, user2);      // no Database attached

        JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);
        userDetailsManager.createUser(user1);
        userDetailsManager.createUser(user2);
        return userDetailsManager;
    }

    @Bean
   public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
