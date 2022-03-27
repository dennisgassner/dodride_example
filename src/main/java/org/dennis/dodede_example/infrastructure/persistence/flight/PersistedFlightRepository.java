package org.dennis.dodede_example.infrastructure.persistence.flight;

import org.springframework.data.repository.CrudRepository;

public interface PersistedFlightRepository extends CrudRepository<PersistedFlight, String> {
}
