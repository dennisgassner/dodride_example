package org.dennis.dodede_example.infrastructure.persistence.flight;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PersistedAircraft {

    @Id
    private String registration;

    private String type;
    @OneToMany
    @JoinColumn(name = "aircraft_registration")
    private List<PersistedSeat> seats = new ArrayList<>();


}

