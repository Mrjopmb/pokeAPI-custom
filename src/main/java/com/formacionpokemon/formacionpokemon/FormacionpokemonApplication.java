package com.formacionpokemon.formacionpokemon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class FormacionpokemonApplication {

	public static void main(String[] args) {
		SpringApplication.run(FormacionpokemonApplication.class, args);
	}

}
