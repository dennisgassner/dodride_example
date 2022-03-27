package org.dennis.dodede_example.business.booking;

import org.dennis.dodede_example.business.booking.aggregate.Booking;
import org.dennis.dodede_example.business.shared.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class BookingAppService  {

    @Autowired
    BookingFactory bookingFactory;

    public Booking bookFlight(String flightNumber, String customerId, List<String> seatIds) {
        Booking booking = bookingFactory.newBooking(flightNumber,customerId,seatIds);
        booking.confirmBooking();
        return booking;
    }

    public Money getBookingTotal(String flightNumber, List<String> seatIds) {
        Booking booking = bookingFactory.newBooking(flightNumber, null, seatIds);
        booking.calculateTotal();
        return booking.getPrice();
    }




}
