package org.dennis.dodede_example.infrastructure.persistence.flight;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Embeddable
@NoArgsConstructor
public class PersistedSeatId implements Serializable {

    private String seatId;
    @OneToOne
    @JoinColumn(name = "aircraft_registration")
    private PersistedAircraft aircraft;

    public PersistedSeatId(String seatId) {
        this.seatId = seatId;
    }

}
