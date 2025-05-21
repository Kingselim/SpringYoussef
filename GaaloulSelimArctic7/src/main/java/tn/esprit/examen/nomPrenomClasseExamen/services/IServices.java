package tn.esprit.examen.nomPrenomClasseExamen.services;

import tn.esprit.examen.nomPrenomClasseExamen.entities.Airline;
import tn.esprit.examen.nomPrenomClasseExamen.entities.Client;
import tn.esprit.examen.nomPrenomClasseExamen.entities.Flight;

import java.util.List;

public interface IServices {
    Client add(Client client);
    //void test();
    //   public void testScheduling();

    Airline addAirline(Airline airline);

    Flight addFlightWithPassengers(Flight flight);

    public void getPassFlight();
public void compensationMilles();

public Airline assignFlightToAirline(List<String> numberOfFlight,String codeIATA);

}
