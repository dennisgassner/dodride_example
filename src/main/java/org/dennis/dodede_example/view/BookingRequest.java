package org.dennis.dodede_example.view;

import lombok.Data;

import java.util.List;

@Data
public class BookingRequest {

    private String flightNumber;
    private String customerID;
    private List<String> bookedSeats;

}
