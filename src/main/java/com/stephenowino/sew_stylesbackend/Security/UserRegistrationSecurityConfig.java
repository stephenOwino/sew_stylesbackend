package com.stephenowino.sew_stylesbackend.Security;

import com.stephenowino.sew_stylesbackend.Role.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity

public class UserRegistrationSecurityConfig {

        @Bean
        public PasswordEncoder passwordEncoder(){
                return new BCryptPasswordEncoder();
        }
        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                http
                        .csrf(Customizer.withDefaults())  // Enabling default CSRF handling (disable if API only)
                        .httpBasic(Customizer.withDefaults())  // Enabling basic authentication (useful for Postman testing)
                        .formLogin(Customizer.withDefaults())  // Removed as per requirement
                        .authorizeHttpRequests(auth -> auth
                                .requestMatchers("/register/**", "/login/**").permitAll()  // Public access for registration and login
                                .requestMatchers("/tailors/**").hasAuthority("ROLE_" + Role.TAILOR.name())  // Tailor access
                                .requestMatchers("/users/**").hasAnyAuthority("ROLE_" + Role.USER.name(), "ROLE_" + Role.ADMIN.name())  // User and Admin access
                                .anyRequest().authenticated()  // Authentication required for all other requests
                        );

                return http.build();
        }
}
