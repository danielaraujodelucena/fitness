package com.bitinterativo.fitness;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class FitnessApplication {

	public static void main(String[] args) {
		SpringApplication.run(FitnessApplication.class, args);
		
		/*
		BCryptPasswordEncoder enconder = new BCryptPasswordEncoder();
		String pass = enconder.encode("123");
		System.out.print(pass);
		*/
		
		// $2a$10$JaSYcc35gfFZu2wOASg5rOorM6KzYta64dvuvJokIY5Ry8ghm7aLO
	}

}
