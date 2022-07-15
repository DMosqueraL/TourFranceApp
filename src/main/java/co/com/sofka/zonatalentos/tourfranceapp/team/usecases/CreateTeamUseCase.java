package co.com.sofka.zonatalentos.tourfranceapp.team.usecases;

import co.com.sofka.zonatalentos.tourfranceapp.team.dto.TeamDTO;
import co.com.sofka.zonatalentos.tourfranceapp.team.mapper.TeamMappers;
import co.com.sofka.zonatalentos.tourfranceapp.team.repository.TeamRepository;
import co.com.sofka.zonatalentos.tourfranceapp.team.usecases.interfaces.SaveTeam;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Service
@Validated
public class CreateTeamUseCase implements SaveTeam {
    private final TeamRepository teamRepository;
    private final TeamMappers mappers;

    public CreateTeamUseCase(TeamRepository teamRepository, TeamMappers mappers) {
        this.teamRepository = teamRepository;
        this.mappers = mappers;
    }

    @Override
    public Mono<TeamDTO> apply(TeamDTO teamDTO) {
        return teamRepository
                .save(mappers.mapTeamDTOToTeam(null).apply(teamDTO))
                .map(mappers.mapTeamToTeamDTO());
    }
}
