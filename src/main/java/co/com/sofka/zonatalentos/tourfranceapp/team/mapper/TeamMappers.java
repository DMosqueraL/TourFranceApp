package co.com.sofka.zonatalentos.tourfranceapp.team.mapper;

import co.com.sofka.zonatalentos.tourfranceapp.team.collection.Team;
import co.com.sofka.zonatalentos.tourfranceapp.team.dto.TeamDTO;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class TeamMappers {
    public Function<TeamDTO, Team> mapTeamDTOToTeam(String id){
        return updateTeam -> {
            var team = new Team();
            team.setIdTeam(id);
            team.setNameTeam(updateTeam.getNameTeam());
            team.setCodeTeam(updateTeam.getCodeTeam());
            team.setPartnerCountry(updateTeam.getPartnerCountry());

            return  team;
        };
    }

    public Function<Team, TeamDTO> mapTeamToTeamDTO(){
        return entity -> new TeamDTO(
                entity.getIdTeam(),
                entity.getNameTeam(),
                entity.getCodeTeam(),
                entity.getPartnerCountry()
        );
    }
}
