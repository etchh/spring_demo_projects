package com.example.reservationservice;

import java.util.Collection;
import java.util.stream.Stream;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Component;

@EnableDiscoveryClient
@SpringBootApplication
public class ReservationServiceApplication {
	
//	CommandLineRunner commandLineRunner (){
//		return new CommandLineRunner(){
//			@Override
//			public void run(String... arg0) throws Exception {
//				// TODO Auto-generated method stub
//				
//			}
//		};
//	}
	
	@Bean
	CommandLineRunner commandLineRunner(ReservationRepository reservationRepository){
		return strings -> {
			Stream.of("Josh", "Pieter", "Tasha", "Eric")
			.forEach(n -> reservationRepository.save(new Reservation(n)));
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(ReservationServiceApplication.class, args);
	}
}

@Entity
class Reservation {

	@Id
	@GeneratedValue
	private Long id;
	private String reservationName;

	public Reservation(String reservationName) {
		super();
		this.reservationName = reservationName;
	}

	public Reservation(){
		
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Reservation{" + "id=" + id + ", reservationName='" + reservationName + '\'' + '}';
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getReservationName() {
		return reservationName;
	}

	public void setReservationName(String reservationName) {
		this.reservationName = reservationName;
	}

}

@RepositoryRestResource
interface ReservationRepository extends JpaRepository<Reservation, Long>{
	
	@RestResource(path = "by-name")
	Collection<Reservation> findByReservationName(@Param("rn") String rn);
}

//@Component
//class DummyDataCLR implements CommandLineRunner{
//	
//	@Autowired
//	private ReservationRepository reservationRepository;
//
//	@Override
//	public void run(String... arg0) throws Exception {
//		Stream.of("Josh", "Pieter", "Tasha", "Eric")
//		.forEach(n -> this.reservationRepository.save(new Reservation(n)));
//		
//	}
//}
