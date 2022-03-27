package org.dennis.dodede_example.business.flight.aggregate;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;


import java.util.ArrayList;
import java.util.List;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Aircraft {

    @EqualsAndHashCode.Include
    private String registration;
    private AIRCRAFT_TYPE type;
    private List<Seat> seats = new ArrayList<>();

    public enum AIRCRAFT_TYPE {
        A319, A320, A321, B737_400, B737_MAX
    }

    public Aircraft(String registration, AIRCRAFT_TYPE type) {
        this.registration = registration;
        this.type = type;
    }

    public List<Seat> getSeats() {
        if(seats==null) {
            seats = new ArrayList<>();
        }
        return seats;
    }


}
