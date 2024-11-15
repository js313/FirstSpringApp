package com.sbear.firstapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectSecurityConfig {
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/dashboard").authenticated()
                        .requestMatchers("/", "/home").permitAll()
                        .requestMatchers("/holidays/**").permitAll()
                        .requestMatchers("/contact").permitAll()
                        .requestMatchers("/saveMsg").permitAll()
                        .requestMatchers("/courses").permitAll()
                        .requestMatchers("/about").permitAll()
                        .requestMatchers("/assets/**").permitAll()
                        .requestMatchers("/login").permitAll()
                        .anyRequest().authenticated()
                        ) // denyAll(), authenticated(), etc
                .httpBasic(Customizer.withDefaults())
                .formLogin(formLogin -> formLogin.loginPage("/login").defaultSuccessUrl("/dashboard").failureUrl("/login?error=true"))
                .logout(logout -> logout.logoutSuccessUrl("/login?logout=true").invalidateHttpSession(true))
                .build();
    }

    @Bean
    InMemoryUserDetailsManager userDetailsManager() {
        UserDetails admin = User.builder().username("admin").password(passwordEncoder().encode("12345")).roles("USER", "ADMIN").build();
        UserDetails user = User.builder().username("user").password(passwordEncoder().encode("12345")).roles("USER").build();

        return new InMemoryUserDetailsManager(user, admin);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
