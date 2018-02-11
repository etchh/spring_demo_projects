package com.example.reservationservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.stream.Stream;

@SpringBootApplication
public class ReservationServiceApplication {

//	@Bean
//	CommandLineRunner commandLineRunner (){
//		return new CommandLineRunner() {
//			@Override
//			public void run(String... strings) throws Exception {
//
//			}
//		};
//	}

	//another way of creating beans, either the following or by creating a whole bean class
	@Bean
	CommandLineRunner commandLineRunner (ReservationRepository reservationRepository){
		return strings -> {
			Stream.of("Josh", "Pieter", "Tasha", "Eric" , "Suzie", "Max")
					.forEach( n -> reservationRepository.save(new Reservation(n)));
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(ReservationServiceApplication.class, args);
	}
}
