package org.dennis.dodede_example;


import org.dennis.dodede_example.business.flight.FlightFactory;
import org.dennis.dodede_example.business.flight.FlightsRepository;
import org.dennis.dodede_example.business.flight.aggregate.Aircraft;
import org.dennis.dodede_example.business.flight.aggregate.Flight;
import org.dennis.dodede_example.business.flight.aggregate.SeatType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


@SpringBootApplication
public class DodrideExampleApplication {

	@Autowired
	FlightFactory flightFactory;

	@Autowired
	FlightsRepository flightsRepository;

	public static void main(String[] args) {
		SpringApplication.run(DodrideExampleApplication.class, args);
	}

	/*
	 *	Init H2 with some data
	 */
	@PostConstruct
	public void initFlights() {
		//first flight
		flightFactory = flightFactory.newFlight("AB12", LocalDateTime.of(LocalDate.now(), LocalTime.of(13,15)), LocalDateTime.of(LocalDate.now(), LocalTime.of(13,45)),
				"EDDF", "EHAM", 40., 10);
		flightFactory = flightFactory.withAircraft("AB-123", Aircraft.AIRCRAFT_TYPE.A319);
		addSeats(flightFactory);
		Flight flight = flightFactory.build();
		flightsRepository.addFlightToSchedule(flight);
		//second flight (back)
		flightFactory = flightFactory.newFlight("AB21", LocalDateTime.of(LocalDate.now(), LocalTime.of(14,15)), LocalDateTime.of(LocalDate.now(), LocalTime.of(14,45)),
				"EHAM", "EDDF", 40., 10);
		flightFactory = flightFactory.withAircraft("AB-123", Aircraft.AIRCRAFT_TYPE.A319);
		addSeats(flightFactory);
		Flight flightBack = flightFactory.build();
		flightsRepository.addFlightToSchedule(flightBack);
 	}

	private void addSeats(FlightFactory flightFactory) {
		flightFactory.addAircraftSeat("A1", new SeatType(SeatType.SEATCLASS.BUSINESS, true, new BigDecimal(2)));
		flightFactory.addAircraftSeat("A2", new SeatType(SeatType.SEATCLASS.BUSINESS, true, new BigDecimal(2)));
		flightFactory.addAircraftSeat("A3", new SeatType(SeatType.SEATCLASS.BUSINESS, true, new BigDecimal(2)));
		flightFactory.addAircraftSeat("A4", new SeatType(SeatType.SEATCLASS.BUSINESS, true, new BigDecimal(2)));
		flightFactory.addAircraftSeat("B1", new SeatType(SeatType.SEATCLASS.ECONOMY, true, new BigDecimal(1.5)));
		flightFactory.addAircraftSeat("B2", new SeatType(SeatType.SEATCLASS.ECONOMY, false, new BigDecimal(1)));
		flightFactory.addAircraftSeat("B3", new SeatType(SeatType.SEATCLASS.ECONOMY, false, new BigDecimal(1)));
		flightFactory.addAircraftSeat("B4", new SeatType(SeatType.SEATCLASS.ECONOMY, false, new BigDecimal(1)));
	}


}
