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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;


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
                        .requestMatchers("/api/admin/**").hasAuthority("ROLE_ADMIN")
                        .requestMatchers("/api/hr/**").hasAuthority("ROLE_HR")// role based authorization
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
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList(
                "http://localhost:3000",  // React
                "http://localhost:4000",  // Other local frontends
                "http://localhost:4200",  // Angular
                "https://ydpmart.vercel.app/"
        ));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type", "Accept"));
        configuration.setExposedHeaders(Collections.singletonList("Authorization"));
        configuration.setAllowCredentials(true);
        configuration.setMaxAge(3600L); // 1 hour

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); // Apply to all endpoints
        return source;
    }


}