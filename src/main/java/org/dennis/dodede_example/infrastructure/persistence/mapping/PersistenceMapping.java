package org.dennis.dodede_example.infrastructure.persistence.mapping;


import org.dennis.dodede_example.business.booking.aggregate.Booking;
import org.dennis.dodede_example.business.flight.FlightFactory;
import org.dennis.dodede_example.business.flight.aggregate.Aircraft;
import org.dennis.dodede_example.business.flight.aggregate.Flight;
import org.dennis.dodede_example.business.flight.aggregate.Seat;
import org.dennis.dodede_example.business.flight.aggregate.SeatType;
import org.dennis.dodede_example.business.shared.SeatID;
import org.dennis.dodede_example.infrastructure.persistence.booking.PersistedBooking;
import org.dennis.dodede_example.infrastructure.persistence.flight.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PersistenceMapping {

    @Autowired
    FlightFactory flightFactory;

    @Autowired
    PersistedSeatRepository persistedSeatRepository;

    private ModelMapper getModelMapperBookingToPersist() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper = getModelMapperFlightToPersist();
        return modelMapper;
    }

    public PersistedBooking mapBookingToPersistedBooking(final Booking booking) {
        PersistedBooking persistedBooking = new PersistedBooking();
        ModelMapper modelMapper = getModelMapperBookingToPersist();
        persistedBooking = modelMapper.map(booking, PersistedBooking.class);
        final PersistedBooking finalPersistedBooking = persistedBooking;
        final PersistedAircraft persistedAircraft = persistedBooking.getFlight().getAircraft();
        List<PersistedSeat> persistedSeatList = persistedBooking.getBookedSeats().stream().map(s -> {
            s.getSeatId().setAircraft(persistedAircraft);
            return persistedSeatRepository.findById(s.getSeatId()).get();
        }).map(s -> {
            s.getBooking().add(finalPersistedBooking);
            return s;
        }).collect(Collectors.toList());
        persistedBooking.setBookedSeats(persistedSeatList);
        return persistedBooking;
    }

    public Flight mapPersistedFlightToFlight(PersistedFlight persistedFlight) {
        flightFactory = flightFactory.newFlight(persistedFlight.getFlightNumber(), persistedFlight.getDepartureTime(),
                persistedFlight.getArrivalTime(),
                persistedFlight.getDepartureAirport().getIcaoCode(),
                persistedFlight.getDestinationAirport().getIcaoCode(),
                persistedFlight.getBasePrice().getAmount(),
                persistedFlight.getExtraFarePercentage());
        flightFactory = flightFactory.withAircraft(persistedFlight.getAircraft().getRegistration(),
                Aircraft.AIRCRAFT_TYPE.valueOf(persistedFlight.getAircraft().getType()));
        persistedFlight.getAircraft().getSeats().forEach(s ->
        {
            flightFactory = flightFactory.addAircraftSeat(
                    s.getSeatId().getSeatId(),
                    new SeatType(
                            SeatType.SEATCLASS.valueOf(s.getSeatClass())
                            , s.isExtraLegroom(), s.getExtraFareFactor()
                    ));
        });
        return flightFactory.build();
    }

    public PersistedFlight mapFlightToPersistedFlight(final Flight flight) {
        ModelMapper modelMapper = getModelMapperFlightToPersist();
        PersistedFlight dbFLight = modelMapper.map(flight, PersistedFlight.class);
        final PersistedAircraft persistedAircraft = dbFLight.getAircraft();
        dbFLight.getAircraft().getSeats().stream().forEach(
                s -> {
                    s.getSeatId().setAircraft(persistedAircraft);
                }
        );
        return dbFLight;
    }

    public Seat mapPersistedSeatToSeat(final PersistedSeat persistedSeat) {
        Seat seat = new Seat(new SeatID(persistedSeat.getSeatId().getSeatId()),
                new SeatType(SeatType.SEATCLASS.valueOf(persistedSeat.getSeatClass()), persistedSeat.isExtraLegroom(), persistedSeat.getExtraFareFactor()));
        return seat;
    }

    private ModelMapper getModelMapperFlightToPersist() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.typeMap(Seat.class, PersistedSeat.class).addMappings(mapper -> {
            mapper.map(src -> src.getSeatNumber().getSeatNumber(), (dest, v) -> dest.setSeatId(
                    new PersistedSeatId((String) v)
            ));
            mapper.map(src -> src.getSeatType().isExtraLegroom(), (dest, v) -> dest.setExtraLegroom(true));
            mapper.map(src -> src.getSeatType().getExtraFareFactor(), (dest, v) -> dest.setExtraFareFactor((BigDecimal) v));
        });
        return modelMapper;
    }


}
