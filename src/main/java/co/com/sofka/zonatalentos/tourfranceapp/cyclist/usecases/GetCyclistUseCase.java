package co.com.sofka.zonatalentos.tourfranceapp.cyclist.usecases;

import co.com.sofka.zonatalentos.tourfranceapp.cyclist.dto.CyclistDTO;
import co.com.sofka.zonatalentos.tourfranceapp.cyclist.mapper.CyclistMappers;
import co.com.sofka.zonatalentos.tourfranceapp.cyclist.repository.CyclistRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.function.Function;

@Service
@Validated
public class GetCyclistUseCase implements Function<String, Mono<CyclistDTO>> {

    private final CyclistRepository cyclistRepository;
    private final CyclistMappers mappers;

    public GetCyclistUseCase(CyclistRepository cyclistRepository, CyclistMappers mappers) {
        this.cyclistRepository = cyclistRepository;
        this.mappers = mappers;
    }

    @Override
    public Mono<CyclistDTO> apply(String id) {
        Objects.requireNonNull(id, "id is required");
        return cyclistRepository.findById(id)
                .map(mappers.mapCyclistToCyclistDTO());
    }
}
