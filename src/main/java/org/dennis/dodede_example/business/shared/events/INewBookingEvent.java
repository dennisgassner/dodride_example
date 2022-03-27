package org.dennis.dodede_example.business.shared.events;

import org.dennis.dodede_example.business.booking.aggregate.Booking;

public interface INewBookingEvent {

    public Booking getBooking();
}
