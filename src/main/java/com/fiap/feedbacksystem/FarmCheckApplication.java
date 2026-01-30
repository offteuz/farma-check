package com.fiap.feedbacksystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class FarmCheckApplication {

	public static void main(String[] args) {
		SpringApplication.run(FarmCheckApplication.class, args);
	}

}
