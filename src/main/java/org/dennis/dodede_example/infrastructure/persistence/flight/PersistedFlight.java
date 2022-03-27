package org.dennis.dodede_example.infrastructure.persistence.flight;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.dennis.dodede_example.infrastructure.persistence.shared.PersistedMoney;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PersistedFlight {

    @Id
    private String flightNumber;

    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;

    @ManyToOne(cascade = {CascadeType.ALL})
    private PersistedAirport departureAirport;
    @ManyToOne(cascade = {CascadeType.ALL})
    private PersistedAirport destinationAirport;
    @ManyToOne(cascade = {CascadeType.REFRESH})
    private PersistedAircraft aircraft;

    @Embedded
    private PersistedMoney basePrice;

    private int extraFarePercentage;

    //BOOKINGS

}
