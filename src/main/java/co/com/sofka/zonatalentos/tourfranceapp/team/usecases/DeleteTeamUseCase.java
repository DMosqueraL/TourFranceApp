package co.com.sofka.zonatalentos.tourfranceapp.team.usecases;

import co.com.sofka.zonatalentos.tourfranceapp.team.repository.TeamRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.function.Function;

@Service
@Validated
public class DeleteTeamUseCase implements Function<String, Mono<Void>> {

    private final TeamRepository teamRepository;

    public DeleteTeamUseCase(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public Mono<Void> apply(String id) {
        Objects.requireNonNull(id, "Id is required");
        return teamRepository.deleteById(id);
    }
}
