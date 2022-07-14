package co.com.sofka.zonatalentos.tourfranceapp.team.dto;

import co.com.sofka.zonatalentos.tourfranceapp.cyclist.collection.Cyclist;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class TeamDTO {
    private String idTeam;
    private String nameTeam;
    private String codeTeam;
    private String partnerCountry;
    private List<Cyclist> cyclistsTeam;

    public TeamDTO(String idTeam, String nameTeam, String codeTeam, String partnerCountry, List<Cyclist> cyclistsTeam) {
        this.idTeam = idTeam;
        this.nameTeam = nameTeam;
        this.codeTeam = codeTeam;
        this.partnerCountry = partnerCountry;
        this.cyclistsTeam = cyclistsTeam;
    }
}
