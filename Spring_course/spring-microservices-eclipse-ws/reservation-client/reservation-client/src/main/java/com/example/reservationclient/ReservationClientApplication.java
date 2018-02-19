package com.example.reservationclient;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.sleuth.Sampler;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

@EnableCircuitBreaker

//Source indicates that this is a source of data(data producer)
@EnableBinding(Source.class)
@EnableZuulProxy
@EnableDiscoveryClient
@SpringBootApplication
public class ReservationClientApplication {

	@Bean
	Sampler sampler(){
		return span -> true;
	}

	public static void main(String[] args) {
		SpringApplication.run(ReservationClientApplication.class, args);
	}
}

@RestController
@RequestMapping("/reservations")
class ReservationApiGatewayRestController {

	@Autowired
	public RestTemplate restTemplate;

	@Autowired
	private Source source;

	@RequestMapping(method = RequestMethod.POST)
	public void writeReservation(@RequestBody Reservation r){

		Message<String> msg = MessageBuilder.withPayload(r.getReservationName()).build();
		this.source.output().send(msg);
	}

	public Collection<String> getReservationNamesFallback(){
		return new ArrayList<>();
	}


	@HystrixCommand(fallbackMethod = "getReservationNamesFallback")
	@RequestMapping(method = RequestMethod.GET, value = "names")
	public Collection<String> getReservationNames(){
		//the returned code is an object that uses hypermedia (HATEOAS.js)
		//Hybermedia is an engine of application state, its the library thats been used to generate
		//the payloads that we had in spring data rest based service including those links in the top and bottom and middle

		//enable Spring HATEOAS support (dependency)

		//here I'd love to have Collection<Reservations>.class passed as a parameter, that's because java doesn't have
		//reified generics (which means they are not available at runtime)
		//check out reified generics at: http://gafter.blogspot.com.eg/2006/11/reified-generics-for-java.html
		//also: https://stackoverflow.com/questions/1927789/why-should-i-care-that-java-doesnt-have-reified-generics

		//why they did so? for back-word compatibility
		//in short: at runtime, java forgets its generic information for instance variables,
		//but retains that information for subclasses

		//uncomment to see the difference
		//List<String> x = new ArrayList<> ();

		//List<String> y = new ArrayList<> () {}; // this is an anonymous subclass of ArrayList which has no overriding methods.


		//we can use the previous information to capture generic information at runtime:

		ParameterizedTypeReference<Resources<Reservation>> ptr = new ParameterizedTypeReference<Resources<Reservation>>() {};

		//response entity is the Http Request envelop that contains headers/status code / ,...

		ResponseEntity<Resources<Reservation>> entity = this.restTemplate.
				exchange("http://reservation-service/reservations" , HttpMethod.GET,/*request body*/ null, ptr);

		return entity
				.getBody()
				.getContent()
				.stream()
				.map(Reservation::getReservationName)
				.collect(Collectors.toList());

	}

	//client side representation (DTO)
	class Reservation{

		public String getReservationName() {
			return reservationName;
		}

		public void setReservationName(String reservationName) {
			this.reservationName = reservationName;
		}

		private String reservationName;
	}
}
