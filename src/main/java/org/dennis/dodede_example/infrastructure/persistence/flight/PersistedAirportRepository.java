package org.dennis.dodede_example.infrastructure.persistence.flight;

import org.springframework.data.repository.CrudRepository;

public interface PersistedAirportRepository extends CrudRepository<PersistedAirport, String> {
}
