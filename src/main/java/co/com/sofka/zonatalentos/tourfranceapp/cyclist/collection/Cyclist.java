package co.com.sofka.zonatalentos.tourfranceapp.cyclist.collection;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "cyclists")
public class Cyclist {
    @Id
    private String idCyclist;
    private String name;
    private String numCompetitor;
    private String nameTeam;
    private String nacionality;

}