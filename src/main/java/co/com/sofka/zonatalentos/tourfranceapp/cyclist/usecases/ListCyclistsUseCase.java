package co.com.sofka.zonatalentos.tourfranceapp.cyclist.usecases;

import co.com.sofka.zonatalentos.tourfranceapp.cyclist.dto.CyclistDTO;
import co.com.sofka.zonatalentos.tourfranceapp.cyclist.mapper.CyclistMappers;
import co.com.sofka.zonatalentos.tourfranceapp.cyclist.repository.CyclistRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;

import java.util.function.Supplier;

@Service
@Validated
public class ListCyclistsUseCase implements Supplier<Flux<CyclistDTO>> {
    private final CyclistRepository cyclistRepository;
    private final CyclistMappers mappers;

    public ListCyclistsUseCase(CyclistRepository cyclistRepository, CyclistMappers mappers) {
        this.cyclistRepository = cyclistRepository;
        this.mappers = mappers;
    }

    @Override
    public Flux<CyclistDTO> get() {
        return cyclistRepository.findAll()
                .map(mappers.mapCyclistToCyclistDTO());
    }
}
