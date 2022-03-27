package org.dennis.dodede_example.infrastructure.persistence.shared;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class PersistedMoney {

    private double amount;
    private String currency;
    private double taxRate;

}
