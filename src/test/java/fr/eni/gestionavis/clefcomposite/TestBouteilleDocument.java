package fr.eni.gestionavis.clefcomposite;

import fr.eni.gestionavis.bo.vin.Bouteille;
import fr.eni.gestionavis.bo.vin.BouteilleId;
import fr.eni.gestionavis.dal.BouteilleRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
public class TestBouteilleDocument {

    @Autowired
    BouteilleRepository bouteilleRepository;

    @Test
    void test00_delete_bouteille() {
        bouteilleRepository.deleteAll();

        assertThat(bouteilleRepository.findAll()).isEmpty();

    }


    @Test
    void test01_save_bouteille() {
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
        bouteilleRepository.save(bouteille);
        //Vérification en base
        Optional<Bouteille> opt = bouteilleRepository.findById(bouteilleId);
        assertThat(opt).isNotNull();
        assertThat(opt.isPresent()).isTrue();

        Bouteille bouteilleDB = opt.get();
        assertThat(bouteilleDB).isNotNull();
        assertThat(bouteilleDB.getNom()).isNotNull();

        log.info(bouteilleDB.toString());
    }

}
