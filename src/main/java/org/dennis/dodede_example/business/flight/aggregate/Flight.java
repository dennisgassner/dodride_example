package org.dennis.dodede_example.business.flight.aggregate;

import lombok.*;
import org.dennis.dodede_example.business.shared.FlightID;
import org.dennis.dodede_example.business.shared.Money;
import org.dennis.dodede_example.business.shared.SeatID;
import org.springframework.stereotype.Component;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.function.BiFunction;

@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Component
public class Flight {

    @EqualsAndHashCode.Include
    @NonNull
    private FlightID flightNumber;
    @NonNull
    private LocalDateTime departureTime;
    @NonNull
    private LocalDateTime arrivalTime;
    @NonNull
    private Airport departureAirport;
    @NonNull
    private Airport destinationAirport;
    @NonNull
    private Aircraft aircraft;
    @NonNull
    private Money basePrice;
    @NonNull
    private int extraFarePercentage;

    private List<Seat> availableSeats;

    public void setAircraft(Aircraft aircraft) {
        this.aircraft = aircraft;
    }

    public void setAvailableSeats(List<Seat> availableSeats) {
        this.availableSeats = availableSeats;
    }

    public boolean isSeatAvailable(SeatID seatID) {
        return !availableSeats.stream().map(seat -> seat.getSeatNumber())
                .anyMatch(seatID1 -> seatID1.equals(seatID));
    }

    public BigDecimal getSeatsExtraFareSum(BiFunction<List<Seat>, List<SeatID>, BigDecimal> function, List<SeatID> bookedSeats) {
        return function.apply(aircraft.getSeats(), bookedSeats);
    }

    public Money getBasePriceTotal() {
        BigDecimal extraFareFactor = new BigDecimal(0);
        if (extraFarePercentage > 0) {
            extraFareFactor = new BigDecimal(extraFarePercentage).divide(BigDecimal.valueOf(100.));
        }
        BigDecimal baseTotal = basePrice.getAmount().multiply(new BigDecimal(1.).add(extraFareFactor));
        return new Money(baseTotal, basePrice.getCurrency(), basePrice.getTaxRate());
    }

    public void updateExtraFarePercentage() {
        BigDecimal aircraftSeats = new BigDecimal(aircraft.getSeats().size());
        BigDecimal bookedSeats = aircraftSeats.subtract(new BigDecimal(availableSeats.size()));
        BigDecimal bookedSeatsRate = bookedSeats.divide(aircraftSeats);

        if(bookedSeatsRate.doubleValue() > 0.15) {
            extraFarePercentage = 15;
        }
        if(bookedSeatsRate.doubleValue() > 0.4) {
            extraFarePercentage = 20;
        }
        if(bookedSeatsRate.doubleValue() > 0.5) {
            extraFarePercentage = 33;
        }
        if(bookedSeatsRate.doubleValue() > 0.75) {
            extraFarePercentage = 50;
        }

    }

}

