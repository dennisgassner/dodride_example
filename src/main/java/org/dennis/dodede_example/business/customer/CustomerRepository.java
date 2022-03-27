package org.dennis.dodede_example.business.customer;

import org.dennis.dodede_example.business.customer.aggregate.*;
import org.dennis.dodede_example.business.shared.CustomerID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.UUID;

@Repository
public class CustomerRepository {

    @Autowired
    CustomerFactory customerFactory;

    /*
    *   Customers are stored inside an external system, so that they can be retrieved using a restful interface.
    *   For this small example application, this external system is mocked by hard coded customer creation.
     */
    public Customer getCustomer(CustomerID customerID) {
        return customerFactory.newCustomer().withID(UUID.randomUUID().toString())
                .withName(new PersonName("Foo"), new PersonName("bar"))
                .withAddress(new Address("Sample Str. 1", "12345", "Nicesamplecity"))
                .withDateOfBirth(new DateOfBirth(LocalDate.of(2000, 1,1)))
                .withEmail(new EMailAddress("my@mail.com"))
                .build();
    }

}
