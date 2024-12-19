package com.example.praticien_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class PraticienServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PraticienServiceApplication.class, args);
	}

}
