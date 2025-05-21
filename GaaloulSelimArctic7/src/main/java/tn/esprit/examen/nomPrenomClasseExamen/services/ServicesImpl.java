package tn.esprit.examen.nomPrenomClasseExamen.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.examen.nomPrenomClasseExamen.entities.*;
import tn.esprit.examen.nomPrenomClasseExamen.repositories.IAirlineRepository;
import tn.esprit.examen.nomPrenomClasseExamen.repositories.IClientRepository;
import tn.esprit.examen.nomPrenomClasseExamen.repositories.IFlightRepository;
import tn.esprit.examen.nomPrenomClasseExamen.repositories.IPassengerRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Slf4j
@RequiredArgsConstructor
@Service
public class ServicesImpl implements IServices {

    private final IClientRepository clientRepository;

    private final IPassengerRepository passengerRepository;
    private final IAirlineRepository airlineRepository;
    private final IFlightRepository flightRepository;
    @Override
    public Client add(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Airline addAirline(Airline airline) {
        return airlineRepository.save(airline);
    }

    @Transactional
    @Override
    public Flight addFlightWithPassengers(Flight flight) {
        Set<Passenger> passengers = flight.getPassengers();
        for(Passenger passenger: passengers){
            passenger.setFlight(flight);
            passengerRepository.save(passenger);
            flight.getPassengers().add(passenger);
        }
        flight.setPassengers(passengers);
        return flightRepository.save(flight);
    }

    @Scheduled(cron = "*/20 * * * * *")
    @Override
    public void getPassFlight() {
        log.info("-------- in getPassFlight ------ ");

        List<Flight> flightList = flightRepository.findAll();

        for(Flight flight: flightList){
            if(flight.getEstimatedDeparture().getTime() < System.currentTimeMillis())
                log.info("le  EstimatedDeparture du flight: "+flight.getNumber()+" est inferieur a la date system");
        }
    }

    @Override
    public void compensationMilles() {
        List<Flight> flightList = flightRepository.findAll();
        List<Passenger> passengerList= passengerRepository.findAll();

    for(Flight flight: flightList) {
        if((flight.getRealDeparture().getTime() - flight.getEstimatedDeparture().getTime()) >= 5 && (flight.getRealDeparture().getTime() - flight.getEstimatedDeparture().getTime()) <15 ){
            log.info("RETARD <= 5 heure && RETARD < 15");
            for(Passenger passenger: flight.getPassengers()){
                passenger.setCompensationMiles(flight.getPrice()/5);
                passengerRepository.save(passenger);

            }
            flight.setStatus(Status.COMPENSATED);
        }
        if((flight.getRealDeparture().getTime() - flight.getEstimatedDeparture().getTime()) >= 15){
            log.info(" RETARD > 15");

            for(Passenger passenger: flight.getPassengers()){
                passenger.setCompensationMiles(flight.getPrice()/3);
                passengerRepository.save(passenger);
            }
            flight.setStatus(Status.COMPENSATED);
        }

        if((flight.getStatus() == Status.CANCELED)){
            log.info(" FLIGHT CANCELED");

            for(Passenger passenger: flight.getPassengers()){
                passenger.setCompensationMiles(flight.getPrice()/2);
                passengerRepository.save(passenger);

            }
            flight.setStatus(Status.COMPENSATED);
            flightRepository.save(flight);
        }
}





    /*
        for(Passenger passenger: passengerList){
            if(passenger.getFlight().getRealDeparture().after(passenger.getFlight().getEstimatedDeparture()))
            {
                float time = passenger.getFlight().getRealDeparture().getTime() - passenger.getFlight().getEstimatedDeparture().getTime();
                log.info("-------le time est :"+time);
                if(time >=5 && time < 15){
                    passenger.setCompensationMiles(passenger.getFlight().getPrice()/5);
                }
                if(time >=15 ){
                    passenger.setCompensationMiles(passenger.getFlight().getPrice()/3);
                }
                if(passenger.getFlight().getStatus() == Status.CANCELED)
                {
                    passenger.setCompensationMiles(passenger.getFlight().getPrice()/2);
                }

            }

        }
*/


    }

    @Override
    public Airline assignFlightToAirline(List<String> numberOfFlight, String codeIATA) {
        Airline airline = airlineRepository.findAirlineByCodeIATA(codeIATA);
        for(String nbr: numberOfFlight) {
            Flight f = flightRepository.findFlightsByNumber(nbr);
            f.setAirline(airline);
            flightRepository.save(f);
        }
        return null;
    }


//   @Scheduled(cron = "*/15 * * * * *")
//    @Override
//    public void test() {
//        log.info("testing");
//    }





    // hedhi moouhema ken nheb namel affectation de produit et categories
   /* @Transactional
    @Override
    public Produit ajouterProduitEtCategories(Produit p) {
        Set<Categorie> categories = p.getCategories();
        for (Categorie c : categories) {
            categorieRepository.save(c);
            p.getCategories().add(c);
        }
        return produitRepository.save(p);
    }


    @Scheduled(fixedRate = 60000)
    @Override
    public void testScheduling() {
        log.info("===== Liste des actions d'aujourd'hui =====");
    }

    */
}
