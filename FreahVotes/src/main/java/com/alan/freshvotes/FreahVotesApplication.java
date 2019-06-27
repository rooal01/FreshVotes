package com.alan.freshvotes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FreahVotesApplication {

	public static void main(String[] args) {
		System.setProperty("server.servlet.context-path", "/alansfreshvotes");
		SpringApplication.run(FreahVotesApplication.class, args);
	}
}
