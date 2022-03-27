package org.dennis.dodede_example.business.flight.aggregate;

import lombok.*;
import org.dennis.dodede_example.business.shared.Country;
import org.springframework.stereotype.Component;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Airport {

    private String airportName, city, icaoCode;
    private Country country;

}
