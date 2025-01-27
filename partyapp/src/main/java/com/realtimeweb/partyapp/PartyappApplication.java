package com.realtimeweb.partyapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class PartyappApplication {

	public static void main(String[] args) {
		SpringApplication.run(PartyappApplication.class, args);
	}

}
