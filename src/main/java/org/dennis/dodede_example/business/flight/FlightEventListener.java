package org.dennis.dodede_example.business.flight;

import org.dennis.dodede_example.business.booking.aggregate.Booking;
import org.dennis.dodede_example.business.flight.aggregate.Flight;
import org.dennis.dodede_example.business.shared.events.NewBookingEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class FlightEventListener {

    @Autowired
    FlightsRepository flightsRepository;

    @EventListener
    void handleReturnedEvent(NewBookingEvent event) {
        Booking booking = event.getBooking();
        Flight flight = flightsRepository.getFlight(booking.getFlight().getFlightNumber());
        flight.updateExtraFarePercentage();
        flightsRepository.updateFlightData(flight);
    }
}
