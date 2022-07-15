package co.com.sofka.zonatalentos.tourfranceapp.team.collection;

import co.com.sofka.zonatalentos.tourfranceapp.cyclist.collection.Cyclist;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "teams")
public class Team {
    @Id
    private String idTeam;

    @NotBlank
    private String nameTeam;

    @NotBlank
    @Indexed(unique = true)
    @Size(min = 1, max=3)
    private String codeTeam;

    @NotBlank
    private String partnerCountry;



}
