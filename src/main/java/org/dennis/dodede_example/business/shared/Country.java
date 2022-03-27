package org.dennis.dodede_example.business.shared;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;


@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Component
public class Country {

    private String countryName;
    private BigDecimal taxRate;

}
