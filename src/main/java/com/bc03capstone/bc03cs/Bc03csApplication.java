package com.bc03capstone.bc03cs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class Bc03csApplication {

	public static void main(String[] args) {
		SpringApplication.run(Bc03csApplication.class, args);
	}

}
