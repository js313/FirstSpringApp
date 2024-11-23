package com.sbear.firstapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
// For JPA ORM
@EnableJpaRepositories("com.sbear.firstapp.repository")
@EntityScan("com.sbear.firstapp.model")
@EnableJpaAuditing(auditorAwareRef = "auditAwareImp")
public class FirstAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(FirstAppApplication.class, args);
	}

}
