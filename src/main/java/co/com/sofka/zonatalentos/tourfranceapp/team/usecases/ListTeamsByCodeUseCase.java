package co.com.sofka.zonatalentos.tourfranceapp.team.usecases;

import co.com.sofka.zonatalentos.tourfranceapp.team.dto.TeamDTO;
import co.com.sofka.zonatalentos.tourfranceapp.team.mapper.TeamMappers;
import co.com.sofka.zonatalentos.tourfranceapp.team.repository.TeamRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Service
@Validated
public class ListTeamsByCodeUseCase implements Function<String, Mono<TeamDTO>> {

    private final TeamRepository teamRepository;
    private final TeamMappers mappers;


    public ListTeamsByCodeUseCase(TeamRepository teamRepository, TeamMappers mappers) {
        this.teamRepository = teamRepository;
        this.mappers = mappers;
    }

    @Override
    public Mono<TeamDTO> apply(String code) {
        return teamRepository.findTeamByCodeTeam(code)
                .map(mappers.mapTeamToTeamDTO());
    }
}
