package fr.eni.gestionavis.dal;

import fr.eni.gestionavis.bo.Avis;
import fr.eni.gestionavis.bo.vin.Bouteille;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AvisRepository extends MongoRepository<Avis, String> {

    List<Avis> findByNoteLessThan(int note);
    
    List<Avis> findByNoteGreaterThan(int note);

    List<Avis> findByNoteGreaterThanEqual(int note);

    List<Avis> findByBouteille(Bouteille bouteille);

    List<Avis> findByClientPseudo(String pseudo);

    List<Avis> findByClientQuantiteCommandeeGreaterThan(int quantiteCommandee);

    List<Avis> findAvisByClientQuantiteCommandeeGreaterThan(int quantiteCommandee);

    List<Avis> findByDateBetween(LocalDateTime debut, LocalDateTime fin);
    
}
