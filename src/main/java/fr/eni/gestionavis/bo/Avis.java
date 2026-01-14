package fr.eni.gestionavis.bo;

import fr.eni.gestionavis.bo.vin.Bouteille;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

@Document(collection = "reviews")
public class Avis {

    @Id
    private String id;


    private int note;

    @Field(name = "commentary")
    private String commentaire;

    private LocalDateTime date;

    private Client client;

    @DBRef
    @Field(name = "bottle_id")
    private Bouteille  bouteille;
}
