package com.backend.apirest.autos.alquilerautos;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AlquilerAutosApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger
			(AlquilerAutosApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(AlquilerAutosApplication.class, args);
		LOGGER.info("Alquiler Autos Application is now running...");
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
