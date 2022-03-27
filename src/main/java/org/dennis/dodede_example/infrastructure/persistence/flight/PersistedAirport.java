package org.dennis.dodede_example.infrastructure.persistence.flight;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PersistedAirport {

    @Id
    private String icaoCode;

    private String airportName;
    private String city;
    @Embedded
    private PersistedCountry country;

}
