package com.example.spring_oauth2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringOauth2JwtApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringOauth2JwtApplication.class, args);
		System.out.println("server started at port 8080");
	}

}
