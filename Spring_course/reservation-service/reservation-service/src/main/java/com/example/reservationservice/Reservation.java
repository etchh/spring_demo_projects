package com.example.reservationservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Collection;


//dummy command line runner
//@Component
//class DummyDataCLR implements CommandLineRunner{
//
//    @Autowired
//    private ReservationRepository reservationRepository;
//
//    @Override
//    public void run(String... strings) throws Exception {
//        Stream.of("Josh", "Pieter", "Tasha", "Eric" , "Suzie", "Max")
//                .forEach( n -> this.reservationRepository.save(new Reservation(n)));
//    }
//}

//exposed as a REST endpoint
@RepositoryRestResource
interface ReservationRepository extends JpaRepository<Reservation, Long>{
    @RestResource(path = "by-name")
    Collection<Reservation> findByReservationName(@Param("rn") String rn);

}

//change value of message live, no need to restart server
@RefreshScope
@RestController
class MessageRestController{

    @Value("${message}")
    private String message;

    @RequestMapping("/message")
    String message(){
        return this.message;
    }

}

@Entity
public class Reservation {

    @Id
    @GeneratedValue
    private Long id;
    private String reservationName;

    public Reservation(String reservationName) {
        this.reservationName = reservationName;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", reservationName='" + reservationName + '\'' +
                '}';
    }

    public Reservation() {

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
