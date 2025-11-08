package com.tiz.learntobank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class LearnToBankApplication {

	public static void main(String[] args) {
		SpringApplication.run(LearnToBankApplication.class, args);
	}

}
