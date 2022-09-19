package com.serasa.desafio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@SpringBootApplication
@EnableWebMvc
public class DesafioApplication {

	public static void main(final String... args) {
		SpringApplication.run(DesafioApplication.class, args);
	}

}
