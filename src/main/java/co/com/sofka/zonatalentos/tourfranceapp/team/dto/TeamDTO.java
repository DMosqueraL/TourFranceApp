package co.com.sofka.zonatalentos.tourfranceapp.team.dto;

import co.com.sofka.zonatalentos.tourfranceapp.cyclist.collection.Cyclist;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.Indexed;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class TeamDTO {
    private String idTeam;
    @NotBlank
    private String nameTeam;
    @NotBlank
    private String codeTeam;
    @NotBlank
    private String partnerCountry;

    @NotBlank
    @Indexed(unique = true)
    @Size(min = 1, max=3)
    private Set<Cyclist> cyclistsTeam;

    public TeamDTO(String idTeam, String nameTeam, String codeTeam, String partnerCountry) {
        this.idTeam = idTeam;
        this.nameTeam = nameTeam;
        this.codeTeam = codeTeam;
        this.partnerCountry = partnerCountry;
    }

    public Set<Cyclist> getCyclistsTeam() {
        this.cyclistsTeam = Optional.ofNullable(cyclistsTeam).orElse(new HashSet<>());
        return cyclistsTeam;
    }
}
