package com.franchiseWorld.EmployeeManagementSystem.Config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;


@Configuration
@ComponentScan(basePackages = "com.franchiseWorld") // Package containing your controllers, services, and repositories
@EntityScan(basePackages = "com.franchiseWorld.EmployeeManagementSystem.Entity") // Package containing your entity classes
public class AppConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        //HttpSecurity is a Spring Security object used to configure security filters, authentication mechanisms, and authorization rules.

        http
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                        // the application will operate in stateless mode, meaning each request will need to carry its authentication information
                )
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/api/admin/**").hasAuthority("ROLE_ADMIN") // role based authorization
                        .requestMatchers("/api/**").authenticated()
                        .anyRequest().permitAll()
                )
                .addFilterBefore(new JwtTokenValidator(), BasicAuthenticationFilter.class) // Adding custom JWT Token Validator
                .csrf(csrf -> csrf.disable())// disables Cross-Site Request Forgery protection.
                .httpBasic(Customizer.withDefaults()) //Enables HTTP Basic authentication (using default settings), which allows clients to send credentials (username and password) using the Authorization header (Base64-encoded).
                .formLogin(form -> form.disable()); // If you don't want form login, disable it

        return http.build();
    }


    @Bean

    public PasswordEncoder passwordEncoder() {  //Hashing algorithm used for password storage.

        return new BCryptPasswordEncoder();
    }

}