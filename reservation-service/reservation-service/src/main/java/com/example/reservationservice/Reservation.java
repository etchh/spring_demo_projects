package com.example.reservationservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Collection;
import java.util.stream.Stream;


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
