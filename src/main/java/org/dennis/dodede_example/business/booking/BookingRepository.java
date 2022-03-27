package org.dennis.dodede_example.business.booking;

import org.dennis.dodede_example.business.booking.aggregate.Booking;
import org.dennis.dodede_example.business.shared.BookingID;
import org.dennis.dodede_example.infrastructure.persistence.PersistencePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BookingRepository {

    PersistencePort persistencePort;

    public BookingRepository(PersistencePort persistencePort) {
        this.persistencePort = persistencePort;
    }

    public BookingID saveBooking(final Booking booking) {
        return persistencePort.saveBooking(booking);
    }

}
