package org.dennis.dodede_example.infrastructure.persistence;

import org.dennis.dodede_example.business.booking.BookingFactory;
import org.dennis.dodede_example.business.booking.aggregate.Booking;
import org.dennis.dodede_example.business.flight.aggregate.Flight;
import org.dennis.dodede_example.business.flight.aggregate.Seat;
import org.dennis.dodede_example.business.shared.BookingID;
import org.dennis.dodede_example.business.shared.FlightID;
import org.dennis.dodede_example.infrastructure.persistence.booking.PersistedBooking;
import org.dennis.dodede_example.infrastructure.persistence.booking.PersistedBookingRepository;
import org.dennis.dodede_example.infrastructure.persistence.flight.*;
import org.dennis.dodede_example.infrastructure.persistence.mapping.PersistenceMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersistencePort {

    @Autowired
    PersistenceMapping persistenceMapping;

    @Autowired
    PersistedBookingRepository persistedBookingRepository;

    @Autowired
    PersistedFlightRepository persistedFlightRepository;

    @Autowired
    PersistedAircraftRepository persistedAircraftRepository;

    @Autowired
    PersistedSeatRepository persistedSeatRepository;

    @Transactional
    public BookingID saveBooking(final Booking booking) {
        PersistedBooking dbBooking = null;
        dbBooking = persistenceMapping.mapBookingToPersistedBooking(booking);
        persistedBookingRepository.save(dbBooking);
        return new BookingID(dbBooking.getBookingId());
    }

    @Transactional
    public void saveFlight(final Flight flight) {
        PersistedFlight dbFLight = persistenceMapping.mapFlightToPersistedFlight(flight);
        PersistedAircraft persistedAircraft = dbFLight.getAircraft();
        ArrayList<PersistedSeat> persistedAircraftSeats = (ArrayList<PersistedSeat>) ((ArrayList)persistedAircraft.getSeats()).clone();
        persistedAircraft.getSeats().clear();
        if(persistedAircraftRepository.findById(persistedAircraft.getRegistration()).isEmpty()) {
            persistedAircraftRepository.save(persistedAircraft);
        }
        persistedAircraftSeats.stream().forEach(s->
        {
            if(persistedSeatRepository.findById(s.getSeatId()).isEmpty()) {
                persistedSeatRepository.save(s);
            }
        });
        persistedAircraft.getSeats().addAll(persistedAircraftSeats);
        persistedAircraftRepository.save(persistedAircraft);
        dbFLight.setAircraft(persistedAircraft);
        persistedFlightRepository.save(dbFLight);
    }

    public List<Seat> getFlightAvailableSeats(FlightID flightID) {
        Optional<PersistedFlight> optPersFlight = persistedFlightRepository.findById(flightID.getFlightNumber());
        PersistedFlight persistedFlight = optPersFlight.orElse(new PersistedFlight());
        return persistedFlight.getAircraft().getSeats().stream()
                .filter(s -> s.getBooking().stream()
                        .map(b -> b.getFlight().getFlightNumber())
                        .noneMatch(f -> f.equals(flightID.getFlightNumber()))
                ).map(s -> persistenceMapping.mapPersistedSeatToSeat(s))
                .collect(Collectors.toList());
    }

    public Optional<Flight> getFlight(FlightID flightID) {
        Optional<PersistedFlight> optPersFlight = persistedFlightRepository.findById(flightID.getFlightNumber());
        Optional<Flight> optFlight;
        if(optPersFlight.isPresent()) {
            PersistedFlight persistedFlight = optPersFlight.get();
            Flight flight = persistenceMapping.mapPersistedFlightToFlight(persistedFlight);
            optFlight = Optional.of(flight);
        } else {
            optFlight = Optional.ofNullable(null);
        }
        return optFlight;
    }

    public List<Flight> getAllFlights() {
        List<Flight> flights = new ArrayList<>();
        persistedFlightRepository.findAll().forEach(pf ->
                {
                    Flight flight = persistenceMapping.mapPersistedFlightToFlight(pf);
                    flights.add(flight);
                }
        );
        return flights;
    }

}
