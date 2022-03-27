package org.dennis.dodede_example.infrastructure.persistence.flight;

import org.springframework.data.repository.CrudRepository;

public interface PersistedSeatRepository extends CrudRepository<PersistedSeat, PersistedSeatId> {
}
