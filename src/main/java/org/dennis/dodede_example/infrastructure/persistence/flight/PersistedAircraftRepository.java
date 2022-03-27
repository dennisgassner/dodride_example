package org.dennis.dodede_example.infrastructure.persistence.flight;

import org.springframework.data.repository.CrudRepository;

public interface PersistedAircraftRepository extends CrudRepository<PersistedAircraft, String> {
}
