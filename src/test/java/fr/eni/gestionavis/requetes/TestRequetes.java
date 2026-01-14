package fr.eni.gestionavis.requetes;

import fr.eni.gestionavis.bo.Avis;
import fr.eni.gestionavis.bo.vin.Bouteille;
import fr.eni.gestionavis.bo.vin.BouteilleId;
import fr.eni.gestionavis.dal.AvisRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
public class TestRequetes {

    @Autowired
    AvisRepository avisRepository;

    @Test
    void test01_findByNoteLessThan_3() {
        List<Avis> listeAvis = avisRepository.findByNoteLessThan(3);
        assertThat(listeAvis).isNotNull();
        assertThat(listeAvis).isNotEmpty();
        assertThat(listeAvis.size()).isGreaterThan(1);
        log.info("Le nombre d'Avis avec une note < 3 est de : " + listeAvis.size());
        log.info(listeAvis.toString());
    }

    @Test
    void test02_findByNoteBouteilleGreaterThan_2() {
        // GreaterThan à 2 pour que ce soit bien note >= 3
       // List<Avis> listeAvis = avisRepository.findByNoteGreaterThan(2);
        List<Avis> listeAvis = avisRepository.findByNoteGreaterThanEqual(3);
        assertThat(listeAvis).isNotNull();
        assertThat(listeAvis).isNotEmpty();
        assertThat(listeAvis.size()).isGreaterThan(1);
        log.info("Le nombre d'Avis avec une note >= 3 est de : " + listeAvis.size());
        log.info(listeAvis.toString());
    }

    @Test
    void test03_findByBouteille() {
        final Bouteille b = Bouteille
                .builder()
                .id(BouteilleId
                        .builder()
                        .idBouteille(18298)
                        .idRegion(3)
                        .idCouleur(1)
                        .build())
                .nom("Vin ENI Edition")
                .build();
        List<Avis> listeAvis = avisRepository.findByBouteille(b);
        assertThat(listeAvis).isNotNull();
        assertThat(listeAvis).isNotEmpty();
        assertThat(listeAvis.size()).isEqualTo(3);
        log.info(listeAvis.toString());
    }

    @Test
    void test04_findByClientPseudo_bobeponge() {
        String pseudo = "bobeponge@email.fr";

        List<Avis> listeAvis = avisRepository.findByClientPseudo(pseudo);
        assertThat(listeAvis).isNotNull();
        assertThat(listeAvis).isNotEmpty();
        assertThat(listeAvis.size()).isEqualTo(3);
        log.info(listeAvis.toString());
    }

    @Test
    void test05_findByClientQuantiteCommandeeGreaterThan_100() {
        int qte = 100;

        // List<Avis> listeAvis = avisRepository.findByClientQuantiteCommandeeGreaterThan(qte);
        List<Avis> listeAvis = avisRepository.findAvisByClientQuantiteCommandeeGreaterThan(qte);
        assertThat(listeAvis).isNotNull();
        assertThat(listeAvis).isNotEmpty();
        assertThat(listeAvis.size()).isEqualTo(4);// 108 - 324 - 400 - 1600
        log.info(listeAvis.toString());
    }

    @Test
    void test06_findByDateBetween() {
        LocalDateTime debut = LocalDateTime.of(2023, 7, 13, 12, 28);
        LocalDateTime fin = LocalDateTime.of(2023, 7, 31, 15, 28);

        List<Avis> listeAvis = avisRepository.findByDateBetween(debut,fin);
        assertThat(listeAvis).isNotNull();
        assertThat(listeAvis).isNotEmpty();
        assertThat(listeAvis.size()).isEqualTo(6);
        log.info(listeAvis.toString());
    }


}
