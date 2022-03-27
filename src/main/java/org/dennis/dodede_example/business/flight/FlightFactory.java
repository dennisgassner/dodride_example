package org.dennis.dodede_example.business.flight;

import lombok.NoArgsConstructor;

import org.dennis.dodede_example.business.flight.aggregate.*;
import org.dennis.dodede_example.business.shared.FlightID;
import org.dennis.dodede_example.business.shared.Money;
import org.dennis.dodede_example.business.shared.SeatID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;


import java.math.BigDecimal;

import java.time.LocalDateTime;

@NoArgsConstructor
@Configuration
public class FlightFactory {

    @Autowired
    private AirportDataService airportDataService;

    private Flight newFlight = new Flight();

    private Aircraft aircraft = new Aircraft();

    public FlightFactory newFlight(String flightNumber, LocalDateTime departureTime, LocalDateTime arrivalTime,
                                   String departureIcao, String destinationIcao, double basePrice, int extraFarePercentage) {
        Airport departure = airportDataService.airportData(departureIcao);
        Airport destination = airportDataService.airportData(destinationIcao);
        Money price = new Money(new BigDecimal(basePrice), "EUR", departure.getCountry().getTaxRate());
        newFlight = new Flight(new FlightID(flightNumber), departureTime, arrivalTime, departure, destination,new Aircraft(), price, extraFarePercentage);
        return this;
    }

    public FlightFactory withAircraft(String registration, Aircraft.AIRCRAFT_TYPE type) {
        aircraft = new Aircraft(registration, type);
        return this;
    }

    public FlightFactory addAircraftSeat(String id, SeatType type) {
        aircraft.getSeats().add(new Seat(new SeatID(id), type));
        return this;
    }

    @Bean
    @Scope(value = "prototype")
    public Flight build() {
        newFlight.setAircraft(aircraft);
        newFlight.setAvailableSeats(aircraft.getSeats());
        return newFlight;
    }

}
