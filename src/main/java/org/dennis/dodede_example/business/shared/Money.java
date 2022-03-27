package org.dennis.dodede_example.business.shared;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Getter
@Component
public class Money {

    private BigDecimal amount;
    private String currency;
    private BigDecimal taxRate;

    public BigDecimal getNet() {
        return this.amount;
    }

    public BigDecimal getGross() {
        return this.amount.multiply(this.taxRate);
    }

}
