package org.dennis.dodede_example.business.flight;



import org.dennis.dodede_example.business.flight.aggregate.Flight;
import org.dennis.dodede_example.business.shared.FlightID;
import org.dennis.dodede_example.business.shared.exceptions.FlightNotFound;
import org.dennis.dodede_example.infrastructure.persistence.PersistencePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class FlightsRepository {

    @Autowired
    PersistencePort persistenceProxy;

    public void updateFlightData(final Flight flight) {
        persistenceProxy.saveFlight(flight);
    }

    public void addFlightToSchedule(final Flight flight) {
        persistenceProxy.saveFlight(flight);
    }

    public List<Flight> getScheduledFlights() {
        return persistenceProxy.getAllFlights().stream().
            map(flight-> {
                flight.setAvailableSeats(persistenceProxy.getFlightAvailableSeats(flight.getFlightNumber()));
                return flight;
            }).collect(Collectors.toList());
    }

    @Transactional
    public Flight getFlight(FlightID flightID) {
        Optional<Flight> optionalFlight = persistenceProxy.getFlight(flightID);
        Flight flight = null;
        if(optionalFlight.isPresent()) {
            flight = optionalFlight.get();
            flight.setAvailableSeats(persistenceProxy.getFlightAvailableSeats(flight.getFlightNumber()));
        } else {
            throw new FlightNotFound("Flight " + flightID.getFlightNumber() + " does not exists");
        }
        return flight;
    }

}


