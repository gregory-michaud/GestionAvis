package fr.eni.gestionavis.association;

import fr.eni.gestionavis.bo.Avis;
import fr.eni.gestionavis.bo.Client;
import fr.eni.gestionavis.bo.vin.Bouteille;
import fr.eni.gestionavis.bo.vin.BouteilleId;
import fr.eni.gestionavis.dal.AvisRepository;
import fr.eni.gestionavis.dal.BouteilleRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
public class TestAssociationAvisBouteille {

    @Autowired
    AvisRepository avisRepository;

    @Autowired
    BouteilleRepository bouteilleRepository;

    @Test
    void test00_delete_avis() {
        avisRepository.deleteAll();
        bouteilleRepository.deleteAll();

        assertThat(avisRepository.findAll()).isEmpty();
        assertThat(bouteilleRepository.findAll()).isEmpty();
    }

    @Test
    void test01_save_avis_bouteille() {
        BouteilleId bouteilleId = BouteilleId
                .builder()
                .idBouteille(2298)
                .idRegion(5)
                .idCouleur(1)
                .build();

        Bouteille bouteille = Bouteille
                .builder()
                .id(bouteilleId)
                .nom("Vin Blanc ENI")
                .build();
        Bouteille bouteilleDB = bouteilleRepository.save(bouteille);

        final Client client = Client
                .builder()
                .pseudo("bobeponge@email.fr")
                .quantiteCommandee(11)
                .build();

        final Avis avis = Avis
                .builder()
                .note(5)
                .commentaire("Doux. A déguster frais")
                .client(client)
                .build();

        // Association avec Bouteille
        avis.setBouteille(bouteilleDB);

        // Sauver
        final Avis avisDB = avisRepository.save(avis);

        // Vérifier que l'identifiant n'est pas nul
        assertThat(avisDB.getId()).isNotNull();
        assertThat(avisDB.getId()).isNotBlank();

        // Vérifier que le Client n'est pas nul
        assertThat(avisDB.getClient()).isNotNull();
        assertThat(avisDB.getClient()).isEqualTo(client);

        // Vérifier que le Bouteille est complet
        assertThat(avisDB.getBouteille().getId()).isNotNull();
        assertThat(avisDB.getBouteille()).isEqualTo(bouteilleDB);

        log.info(avisDB.toString());
    }

}
