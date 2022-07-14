package co.com.sofka.zonatalentos.tourfranceapp.cyclist.dto;

import lombok.*;


@NoArgsConstructor
@Data
public class CyclistDTO {
    private String idCyclist;
    private String name;
    private String numCompetitor;
    private String nameTeam;
    private String nacionality;

    public CyclistDTO(String idCyclist, String name, String numCompetitor, String nameTeam, String nacionality) {
        this.idCyclist = idCyclist;
        this.name = name;
        this.numCompetitor = numCompetitor;
        this.nameTeam = nameTeam;
        this.nacionality = nacionality;
    }
}
