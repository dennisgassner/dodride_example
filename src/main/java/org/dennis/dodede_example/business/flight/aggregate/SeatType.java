package org.dennis.dodede_example.business.flight.aggregate;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;


import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@EqualsAndHashCode
public class SeatType {

    private SEATCLASS seatClass;
    private boolean extraLegroom;
    private BigDecimal extraFareFactor;

    public SeatType(SEATCLASS seatClass, boolean extraLegroom, BigDecimal extraFareFactor) {
        this.seatClass = seatClass;
        this.extraLegroom = extraLegroom;
        if(extraFareFactor.doubleValue() >= 1.) {
            this.extraFareFactor = extraFareFactor;
        } else {
            this.extraFareFactor = BigDecimal.ONE;
        }
    }

    public enum SEATCLASS {
        ECONOMY, BUSINESS
    }


}
