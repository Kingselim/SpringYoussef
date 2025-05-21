package tn.esprit.examen.nomPrenomClasseExamen.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.examen.nomPrenomClasseExamen.entities.Client;

public interface IClientRepository extends JpaRepository<Client, Long> {
    //@Query("select l.titre from Livre l inner join l.Actions action where action.type = 'EMPRUNT' and l.bibliotheque.idBibliotheque in :idsBibliotheque")
    //List<String> listerLivresTitresParBibliothequesIds(Long idsBibliotheque);
}