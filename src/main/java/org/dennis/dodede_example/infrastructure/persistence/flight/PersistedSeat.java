package org.dennis.dodede_example.infrastructure.persistence.flight;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.dennis.dodede_example.infrastructure.persistence.booking.PersistedBooking;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PersistedSeat {

    @EmbeddedId
    private PersistedSeatId seatId;

    private String seatClass;
    private boolean extraLegroom;
    private BigDecimal extraFareFactor;

    @ManyToMany(mappedBy ="bookedSeats")
    private List<PersistedBooking> booking = new ArrayList<>();

}
