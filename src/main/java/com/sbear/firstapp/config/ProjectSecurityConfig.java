package com.sbear.firstapp.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
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
                .csrf(csrf -> csrf.ignoringRequestMatchers("/saveMsg").ignoringRequestMatchers("/login").ignoringRequestMatchers("/public/**"))
                .authorizeHttpRequests(authorize -> authorize
                                .requestMatchers("/dashboard").authenticated()
//                                .requestMatchers(PathRequest.toH2Console()).authenticated() // OR "/h2-console/**
                                .requestMatchers("/displayMsgs").hasRole("ADMIN")
                                .requestMatchers("/closeMsg/**").hasRole("ADMIN")
                                .requestMatchers("/displayProfile").authenticated()
                                .requestMatchers("/updateProfile").authenticated()
                                .requestMatchers("/", "/home").permitAll()
                                .requestMatchers("/holidays/**").permitAll()
                                .requestMatchers("/contact").permitAll()
                                .requestMatchers("/saveMsg").permitAll()
                                .requestMatchers("/courses").permitAll()
                                .requestMatchers("/about").permitAll()
                                .requestMatchers("/assets/**").permitAll()
                                .requestMatchers("/public/**").permitAll()
                                .requestMatchers("/login").permitAll()
                                .anyRequest().authenticated()
                        ) // denyAll(), authenticated(), etc
//                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                .httpBasic(Customizer.withDefaults())
                .formLogin(formLogin -> formLogin.loginPage("/login").defaultSuccessUrl("/dashboard").failureUrl("/login?error=true").permitAll())
                .logout(logout -> logout.logoutSuccessUrl("/login?logout=true").invalidateHttpSession(true).permitAll())
                .build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
