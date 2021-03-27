package com.hackaton.flags.flagsbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication

public class FlagsBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlagsBackendApplication.class, args);
	}

}
