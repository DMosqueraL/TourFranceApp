package co.com.sofka.zonatalentos.tourfranceapp.cyclist.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Data
public class CyclistDTO {
    private String idCyclist;
    @NotBlank
    private String name;
    @NotBlank
    @Indexed(unique = true)
    @Size(min = 1, max=3)
    private String numCompetitor;
    @NotBlank
    private String nameTeam;
    @NotBlank
    private String nationality;

    public CyclistDTO(String idCyclist, String name, String numCompetitor, String nameTeam, String nationality) {
        this.idCyclist = idCyclist;
        this.name = name;
        this.numCompetitor = numCompetitor;
        this.nameTeam = nameTeam;
        this.nationality = nationality;
    }
}
