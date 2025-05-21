package tn.esprit.examen.nomPrenomClasseExamen.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tn.esprit.examen.nomPrenomClasseExamen.entities.Airline;
import tn.esprit.examen.nomPrenomClasseExamen.entities.Client;

import java.util.List;

public interface IAirlineRepository extends JpaRepository<Airline, Long> {
   // @Query("select * from Airline a");
   // List<String> getAirlinesByCompensationsMiles();


    Airline findAirlineByCodeIATA(String codeIATA);
}
