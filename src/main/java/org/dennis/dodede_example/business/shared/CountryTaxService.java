package org.dennis.dodede_example.business.shared;


import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CountryTaxService {

    /*
    * This service provides a country's tax rate.
    * In our fictitious domain, a price's tax rate depends on the departure airport
     */
    public Country getTaxByCountry(Country country ) {
        return switch (country.getCountryName()) {
            case "Germany" -> new Country(country.getCountryName(), new BigDecimal(1.19));
            case "France" -> new Country(country.getCountryName(), new BigDecimal(1.20));
            case "Netherlands" -> new Country(country.getCountryName(), new BigDecimal(1.21));
            default -> throw new CountryNotFoundException("Country " + country + " not found");
        };
    }

    public class CountryNotFoundException extends IllegalArgumentException {
        public CountryNotFoundException(String msg) {
            super(msg);
        }
    }

}
