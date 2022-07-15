package co.com.sofka.zonatalentos.tourfranceapp.team.usecases;

import co.com.sofka.zonatalentos.tourfranceapp.team.dto.TeamDTO;
import co.com.sofka.zonatalentos.tourfranceapp.team.mapper.TeamMappers;
import co.com.sofka.zonatalentos.tourfranceapp.team.repository.TeamRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;

import java.util.function.Supplier;

@Service
@Validated
public class ListTeamsUseCase implements Supplier<Flux<TeamDTO>> {

    private final TeamRepository teamRepository;
    private final TeamMappers mappers;

    public ListTeamsUseCase(TeamRepository teamRepository, TeamMappers mappers) {
        this.teamRepository = teamRepository;
        this.mappers = mappers;
    }

    @Override
    public Flux<TeamDTO> get() {
        return teamRepository.findAll()
                .map(mappers.mapTeamToTeamDTO());
    }
}
