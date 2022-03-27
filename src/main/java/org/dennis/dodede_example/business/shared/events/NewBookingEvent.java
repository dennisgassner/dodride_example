package org.dennis.dodede_example.business.shared.events;

import org.dennis.dodede_example.business.booking.aggregate.Booking;
import org.springframework.context.ApplicationEvent;

public class NewBookingEvent extends ApplicationEvent implements INewBookingEvent {

    public NewBookingEvent(Booking source) {
        super(source);
    }

    public Booking getBooking() {
        return (Booking) getSource();
    }

}
