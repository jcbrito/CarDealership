package com.mthree.cardealership;

import com.mthree.cardealership.entities.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CardealershipApplication {

	public static void main(String[] args) {
		SpringApplication.run(CardealershipApplication.class, args);
	}
}