package org.dennis.dodede_example.business.customer.aggregate;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Validated
public class EMailAddress {

    @Email
    private String mailAddress;

}
