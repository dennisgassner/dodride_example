package org.dennis.dodede_example.infrastructure.persistence.booking;

import org.dennis.dodede_example.infrastructure.persistence.flight.PersistedFlight;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PersistedBookingRepository extends CrudRepository<PersistedBooking, String> {

    List<PersistedBooking> findByFlight(PersistedFlight persistedFlight);

}
