package org.dennis.dodede_example.business.booking;

import org.dennis.dodede_example.business.booking.aggregate.Booking;
import org.dennis.dodede_example.business.flight.FlightsRepository;
import org.dennis.dodede_example.business.flight.aggregate.Flight;
import org.dennis.dodede_example.business.shared.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;


import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class BookingFactory {

    FlightsRepository flightsRepository;

    public BookingFactory(FlightsRepository flightsRepository) {
        this.flightsRepository = flightsRepository;
    }

    @Bean
    @Scope(value = "prototype")
    public Booking newBooking(String flightNumber, String customerId, List<String> seatIds) {
        Flight flight = flightsRepository.getFlight(new FlightID(flightNumber));
        Booking booking = new Booking(new CustomerID(customerId), flight,
                    seatIds.stream().map(s->new SeatID(s)).collect(Collectors.toList())
            );
        return booking;
    }


}
