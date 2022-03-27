package org.dennis.dodede_example.business.customer.aggregate;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.dennis.dodede_example.business.shared.CustomerID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Customer {

    @EqualsAndHashCode.Include
    private CustomerID customerID;
    private PersonName firstName;
    private PersonName lastName;
    private Address address;
    private DateOfBirth dateOfBirth;
    private EMailAddress emailAddress;

}
