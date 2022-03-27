package org.dennis.dodede_example.infrastructure.persistence.flight;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class PersistedCountry {

    private String countryName;
    private double taxRate;

}
