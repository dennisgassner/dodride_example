package org.dennis.dodede_example.business.shared.exceptions;

public class FlightNotFound extends IllegalArgumentException {
    public FlightNotFound(String arg) {
        super(arg);
    }
}
