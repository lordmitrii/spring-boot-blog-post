package com.example.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
          .authorizeHttpRequests(authorize -> authorize
            .requestMatchers("/", "/blogs", "/css/**", "/js/**", "/register").permitAll()
            .requestMatchers("/h2-console/**").hasRole("ADMIN")
              .anyRequest().authenticated()
          )
          .csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**"))
            .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable()))
          
          
          .formLogin(form -> form
              .loginPage("/login")
              .defaultSuccessUrl("/", true)
              .permitAll()        
          )
          
        .logout(logout -> logout
            .permitAll()
          );
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public UserDetailsService users() {
        return new InMemoryUserDetailsManager(
            User.withUsername("admin")
                .password(passwordEncoder().encode("1234"))
                .roles("ADMIN")
                .build(),

            User.withUsername("user")
                .password(passwordEncoder().encode("1234"))
                .roles("USER")
                .build()
        );
    }
}
