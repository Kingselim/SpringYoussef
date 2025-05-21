package tn.esprit.examen.nomPrenomClasseExamen.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tn.esprit.examen.nomPrenomClasseExamen.entities.Flight;
import tn.esprit.examen.nomPrenomClasseExamen.entities.Passenger;

import java.util.List;

public interface IFlightRepository extends JpaRepository<Flight, Long> {
    Flight findFlightsByNumber(String nbr);
}
