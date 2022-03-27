package org.dennis.dodede_example.business.flight;

import org.dennis.dodede_example.business.flight.aggregate.Flight;
import org.dennis.dodede_example.business.shared.FlightID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FlightAppService {

    @Autowired
    FlightsRepository flightsRepository;

    public List<Flight> getAvailableFlights() {
        return flightsRepository.getScheduledFlights().stream().filter(flight -> flight.getAvailableSeats().size() > 0).collect(Collectors.toList());
    }

    public Flight getFlightById(FlightID flightID) {
        return flightsRepository.getFlight(flightID);
    }

}
