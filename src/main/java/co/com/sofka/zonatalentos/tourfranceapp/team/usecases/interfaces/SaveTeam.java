package co.com.sofka.zonatalentos.tourfranceapp.team.usecases.interfaces;

import co.com.sofka.zonatalentos.tourfranceapp.team.dto.TeamDTO;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@FunctionalInterface
public interface SaveTeam {
    Mono<TeamDTO> apply(@Valid TeamDTO teamDTO);
}
