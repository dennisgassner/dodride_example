package org.dennis.dodede_example.infrastructure.persistence.booking;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.dennis.dodede_example.infrastructure.persistence.flight.PersistedFlight;
import org.dennis.dodede_example.infrastructure.persistence.flight.PersistedSeat;
import org.dennis.dodede_example.infrastructure.persistence.shared.PersistedMoney;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PersistedBooking {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String bookingId;
    private String customerId;
    @ManyToOne
    private PersistedFlight flight;
    @Embedded
    private PersistedMoney price;
    @ManyToMany
    private List<PersistedSeat> bookedSeats = new ArrayList<>();


}
