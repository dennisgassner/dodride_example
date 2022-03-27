package org.dennis.dodede_example.business.booking.aggregate;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.dennis.dodede_example.business.booking.BookingRepository;
import org.dennis.dodede_example.business.flight.aggregate.Flight;
import org.dennis.dodede_example.business.flight.aggregate.Seat;
import org.dennis.dodede_example.business.shared.*;
import org.dennis.dodede_example.business.shared.events.EventsPublisher;
import org.dennis.dodede_example.business.shared.events.NewBookingEvent;
import org.dennis.dodede_example.business.shared.exceptions.BookingNotPossible;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Component
public class Booking {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private EventsPublisher publisher;

    @EqualsAndHashCode.Include
    private BookingID bookingId;
    private CustomerID customer;
    private Flight flight;
    private Money price;
    private List<SeatID> bookedSeats = new ArrayList<>();

    public Booking(CustomerID customer, Flight flight, List<SeatID> bookedSeats) {
        this.customer = customer;
        this.flight = flight;
        this.bookedSeats = bookedSeats;
    }

    public void confirmBooking() {
        calculateTotal();
        boolean allSeatsAvailable = bookedSeats.stream().allMatch(seatID -> flight.isSeatAvailable(seatID));
        if (allSeatsAvailable) {
            throw new BookingNotPossible("At least one seat is no longer available");
        } else {
            bookingId = bookingRepository.saveBooking(this);
            publisher.publishEvent(new NewBookingEvent(this));
        }
    }

    public void calculateTotal() {
        BiFunction<List<Seat>, List<SeatID>, BigDecimal> function = (allSeats, bookedSeats) -> {
            return allSeats.stream().filter(aircraftSeat -> bookedSeats.stream()
                    .anyMatch(seatId -> seatId.equals(aircraftSeat.getSeatNumber()))
                ).map(s -> s.getSeatType().getExtraFareFactor())
                .reduce(new BigDecimal(0), (subTotal, seatFareFactor) -> subTotal = subTotal.add(seatFareFactor));
        };
        Money flightBaseTotal = flight.getBasePriceTotal();
        BigDecimal seatsExtraFareSum = flight.getSeatsExtraFareSum(function, bookedSeats);
        price = new Money(flightBaseTotal.getAmount().multiply(seatsExtraFareSum), flightBaseTotal.getCurrency(), flightBaseTotal.getTaxRate());
    }

    public Flight getFlight() {
        return flight;
    }

    public Money getPrice() {
        return price;
    }

    public BookingID getBookingId() {
        return bookingId;
    }

    public List<SeatID> getBookedSeats() {
        return bookedSeats;
    }

}
