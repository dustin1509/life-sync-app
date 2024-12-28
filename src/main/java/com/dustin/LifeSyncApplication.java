package com.dustin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
public class LifeSyncApplication {

	public static void main(String[] args) {
		SpringApplication.run(LifeSyncApplication.class, args);
	}
}