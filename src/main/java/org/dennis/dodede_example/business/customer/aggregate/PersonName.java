package org.dennis.dodede_example.business.customer.aggregate;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Validated
public class PersonName {

    @NotEmpty
    @Pattern(regexp = "^[a-zA-Z_]+$")
    private String name;

}
