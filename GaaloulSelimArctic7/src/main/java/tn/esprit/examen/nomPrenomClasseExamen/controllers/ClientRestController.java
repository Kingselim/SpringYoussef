package tn.esprit.examen.nomPrenomClasseExamen.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.examen.nomPrenomClasseExamen.entities.Airline;
import tn.esprit.examen.nomPrenomClasseExamen.entities.Client;
import tn.esprit.examen.nomPrenomClasseExamen.entities.Flight;
import tn.esprit.examen.nomPrenomClasseExamen.services.IServices;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("examen")
@RestController
public class ClientRestController {
    private final IServices services;

    @PostMapping("/add")
    public Client add(@RequestBody Client client){
        return  services.add(client);
    }


    @PostMapping("/addAirline")
    public Airline addAirline(@RequestBody Airline airline){
        return  services.addAirline(airline);
    }

    @PostMapping("/ajouterflightEtPassangers")
    public Flight addFlightWithPassengers(@RequestBody Flight flight) {
        return services.addFlightWithPassengers(flight);
    }

    @PutMapping("/assignFlightToAirline/{numberOfFlight}")
    public Airline assignFlightToAirline(@PathVariable List<String> numberOfFlight, String codeIATA){
        return services.assignFlightToAirline(numberOfFlight,codeIATA);
    }

    @PutMapping("/compensationMilles")
    public void compensationMilles(){
        services.compensationMilles();
    }



}
