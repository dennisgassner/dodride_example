package org.dennis.dodede_example.view;

import org.dennis.dodede_example.business.booking.BookingAppService;
import org.dennis.dodede_example.business.booking.aggregate.Booking;
import org.dennis.dodede_example.business.flight.FlightAppService;
import org.dennis.dodede_example.business.flight.aggregate.Flight;
import org.dennis.dodede_example.business.shared.BookingID;
import org.dennis.dodede_example.business.shared.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
public class FlightBookingController {

    @Autowired
    BookingAppService bookingAppService;

    @Autowired
    FlightAppService flightAppService;

    @GetMapping("flights")
    public List<UIFlight> getAllAvailableFlights() {
        List<Flight> allFlights = flightAppService.getAvailableFlights();
        List<UIFlight> uiFlights = new ArrayList<>();
        allFlights.stream().forEach(f->
        {
            UIFlight uiFlight = new UIFlight();
            uiFlight.setFlightID(f.getFlightNumber());
            uiFlight.setDepartureTime(f.getDepartureTime());
            uiFlight.setArrivalTime(f.getArrivalTime());
            uiFlight.setDepartureAirport(f.getDepartureAirport());
            uiFlight.setDestinationAirport(f.getDestinationAirport());
            uiFlight.setAircraftType(f.getAircraft().getType().name());
            uiFlight.setEconomyPrice(f.getBasePriceTotal().getGross().doubleValue());
            uiFlight.setAvailableSeats(f.getAvailableSeats());
            uiFlights.add(uiFlight);
        });
        return uiFlights;
    }


    @PostMapping("booking")
    public BookingID bookFlight(@RequestBody BookingRequest bookingRequest) {
        Booking booking = bookingAppService.bookFlight(bookingRequest.getFlightNumber(), bookingRequest.getCustomerID(), bookingRequest.getBookedSeats());
        return booking.getBookingId();
    }

    @PostMapping("total")
    public BigDecimal getTotal(@RequestBody BookingRequest bookingRequest) {
        Money total = bookingAppService.getBookingTotal(bookingRequest.getFlightNumber(),bookingRequest.getBookedSeats());
        return total.getGross();
    }

}
