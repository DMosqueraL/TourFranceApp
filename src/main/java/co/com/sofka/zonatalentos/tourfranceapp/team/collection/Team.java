package co.com.sofka.zonatalentos.tourfranceapp.team.collection;

import co.com.sofka.zonatalentos.tourfranceapp.cyclist.collection.Cyclist;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "teams")
public class Team {
    @Id
    private String idTeam;
    private String nameTeam;
    private String codeTeam;
    private String partnerCountry;
    private List<Cyclist> cyclistsTeam;
}
