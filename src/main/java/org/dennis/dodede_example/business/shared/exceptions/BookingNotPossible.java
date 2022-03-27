package org.dennis.dodede_example.business.shared.exceptions;

public class BookingNotPossible extends RuntimeException {
    public BookingNotPossible(String msg) {
        super(msg);
    }
}
