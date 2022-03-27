package org.dennis.dodede_example.business.flight;

import org.dennis.dodede_example.business.flight.aggregate.Airport;
import org.dennis.dodede_example.business.shared.Country;
import org.dennis.dodede_example.business.shared.CountryTaxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AirportDataService {

    @Autowired
    CountryTaxService countryTaxService;

    /* This AirportService could connect to an external API that
    * provides full airport data based on the transmitted icao code.
    * Due the necessity of a valid api-key this service actual only mocks such a webservice.
    */
    public Airport airportData(String icaoCode) {
        return switch (icaoCode) {
            case "EDDF" -> new Airport("Frankfurt Main Airport", "Frankfurt Main", icaoCode, countryTaxService.getTaxByCountry(new Country("Germany", null)));
            case "LFPG" -> new Airport("Charles de Gaulle Airport", "Paris", icaoCode, countryTaxService.getTaxByCountry(new Country("France", null)));
            case "EHAM" -> new Airport("Amsterdam Schiphol Airport", "Amsterdam", icaoCode, countryTaxService.getTaxByCountry(new Country("Netherlands", null)));
            default -> throw new AirportNotFound("Airport " + icaoCode + " not found");
        };
    }

    public class AirportNotFound extends IllegalArgumentException {
        public AirportNotFound(String msg) {
            super(msg);
        }
    }


}
