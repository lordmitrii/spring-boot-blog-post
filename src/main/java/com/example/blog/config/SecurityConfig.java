package com.example.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.blog.repository.UserRepository;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
          .authorizeHttpRequests(authorize -> authorize
            .requestMatchers("/", "/blogs", "/css/**", "/js/**", "/register", "/login").permitAll()
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
            .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
            .logoutSuccessUrl("/login?logout")
            .permitAll()
          );
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
      return username -> {
          var user = userRepository.findByUsername(username);
          if (user == null) {
              throw new UsernameNotFoundException("User not found: " + username);
          }
          // Build UserDetails in one fluent call to ensure correct typing
          return org.springframework.security.core.userdetails.User
                  .withUsername(user.getUsername())
                  .password(user.getPassword())
                  .accountLocked(!user.isEnabled())
                  .roles(user.getRoles()
                          .stream()
                          .map(role -> role.name().replace("ROLE_", ""))
                          .toArray(String[]::new))
                  .build();
      };
    }
}
