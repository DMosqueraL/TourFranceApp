package co.com.sofka.zonatalentos.tourfranceapp.cyclist.usecases;

import co.com.sofka.zonatalentos.tourfranceapp.cyclist.dto.CyclistDTO;
import co.com.sofka.zonatalentos.tourfranceapp.cyclist.mapper.CyclistMappers;
import co.com.sofka.zonatalentos.tourfranceapp.cyclist.repository.CyclistRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;

import java.util.function.Function;

@Service
@Validated
public class ListCyclistsByNationalityUseCase implements Function<String, Flux<CyclistDTO>> {

    private final CyclistRepository cyclistRepository;
    private final CyclistMappers mappers;

    public ListCyclistsByNationalityUseCase(CyclistRepository cyclistRepository, CyclistMappers mappers) {
        this.cyclistRepository = cyclistRepository;
        this.mappers = mappers;
    }

    @Override
    public Flux<CyclistDTO> apply(String nationality) {
        return cyclistRepository.findCyclistsByNationality(nationality)
                .map(mappers.mapCyclistToCyclistDTO());
    }
}
