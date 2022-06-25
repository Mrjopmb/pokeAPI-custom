package com.formacionpokemon.formacionpokemon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ComponentScan("com.formacionpokemon.formacionpokemon.controller")
public class FormacionpokemonApplication {

	public static void main(String[] args) {
		SpringApplication.run(FormacionpokemonApplication.class, args);
	}
	

}
