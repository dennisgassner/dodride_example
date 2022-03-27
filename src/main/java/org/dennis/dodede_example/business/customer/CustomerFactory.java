package org.dennis.dodede_example.business.customer;

import lombok.NoArgsConstructor;
import org.dennis.dodede_example.business.customer.aggregate.*;
import org.dennis.dodede_example.business.shared.CustomerID;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;



@NoArgsConstructor
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CustomerFactory {

    private String customerID;
    private PersonName firstName, lastName;
    private Address address;
    private DateOfBirth dateOfBirth;
    private EMailAddress emailAddress;

    public CustomerFactory newCustomer() {
        customerID = null;
        firstName = null;
        lastName = null;
        address = null;
        dateOfBirth = null;
        emailAddress = null;
        return this;
    }

    public CustomerFactory withID(String customerID) {
        this.customerID = customerID;
        return this;
    }

    public CustomerFactory withName(PersonName firstName, PersonName lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        return this;
    }

    public CustomerFactory withAddress(Address address) {
        this.address = address;
        return this;
    }

    public CustomerFactory withDateOfBirth(DateOfBirth dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    public CustomerFactory withEmail(EMailAddress emailAddress) {
        this.emailAddress = emailAddress;
        return this;
    }

    public Customer build() {
        return new Customer(new CustomerID(customerID), firstName, lastName, address, dateOfBirth, emailAddress);
    }



}
