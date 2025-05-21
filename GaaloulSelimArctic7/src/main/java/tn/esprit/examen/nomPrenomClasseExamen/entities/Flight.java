package tn.esprit.examen.nomPrenomClasseExamen.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
@Entity
public class Flight implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Long idFlight;

    String number;
    Date estimatedDeparture;
    Date realDeparture;
    float price;

    @Enumerated(EnumType.STRING)
    Status status;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="flight")
    private Set<Passenger> Passengers;

    @ManyToOne
    Airline airline;


}
