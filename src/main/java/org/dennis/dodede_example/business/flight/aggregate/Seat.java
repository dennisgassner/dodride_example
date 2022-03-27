package org.dennis.dodede_example.business.flight.aggregate;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.dennis.dodede_example.business.shared.SeatID;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Seat {

    @EqualsAndHashCode.Include
    private SeatID seatNumber;
    private SeatType seatType;

}
