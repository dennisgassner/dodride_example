package org.dennis.dodede_example.view;

import lombok.Data;
import org.dennis.dodede_example.business.flight.aggregate.Airport;
import org.dennis.dodede_example.business.flight.aggregate.Seat;
import org.dennis.dodede_example.business.shared.FlightID;


import java.time.LocalDateTime;
import java.util.List;

@Data
public class UIFlight {

    private FlightID flightID;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private Airport departureAirport;
    private Airport destinationAirport;
    private String aircraftType;
    private Double economyPrice;
    private List<Seat> availableSeats;


}
