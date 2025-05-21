package tn.esprit.examen.nomPrenomClasseExamen.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
@Entity
public class Passenger implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Long idPassenger;
    String lastName;
    String firstName;
    float compensationMiles;

    @ManyToOne
    Flight flight;
}
