package com.greennplanne.aeroplane;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableJpaRepositories
@EnableAsync
public class AeroplaneNetworkApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AeroplaneNetworkApiApplication.class, args);
	}

}
