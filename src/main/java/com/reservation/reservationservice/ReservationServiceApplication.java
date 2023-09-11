package com.reservation.reservationservice;

import com.reservation.reservationservice.model.Address;
import com.reservation.reservationservice.repository.AddressRepository;
import com.reservation.reservationservice.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ReservationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReservationServiceApplication.class, args);
	}

}
