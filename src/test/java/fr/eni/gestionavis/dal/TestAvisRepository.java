package fr.eni.gestionavis.dal;

import fr.eni.gestionavis.bo.Avis;
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
public class TestAvisRepository {

    @Autowired
    AvisRepository avisRepository;

    @Test
    void test00_delete_avis() {
        avisRepository.deleteAll();

        assertThat(avisRepository.findAll()).isEmpty();

    }

    @Test
    void test01_save_avis_sans_commentaire_cours() {
        Avis avis = Avis
                .builder()
                .note(4)
                .commentaire("Vin délicieux")
                .date(LocalDateTime.now())
                .build();
        Avis avisDB = avisRepository.save(avis);

        //Vérifier que l'identifiant n'est pas nul
        assertThat(avisDB.getId()).isNotNull();
        assertThat(avisDB.getId()).isNotBlank();
        //Vérifier que tous les attributs sont remplis et non nuls
        assertThat(avisDB.getNote()).isEqualTo(4);
        assertThat(avisDB.getCommentaire()).isNotNull();
        assertThat(avisDB.getCommentaire()).isNotBlank();
        assertThat(avisDB.getDate()).isNotNull();

        log.info(avisDB.toString());
    }

    @Test
    void test02_findAll_avis() {
        List<Avis> listeAvis = avisRepository.findAll();
        assertThat(listeAvis).isNotNull();
        assertThat(listeAvis).isNotEmpty();
        log.info(listeAvis.toString());
    }

}
