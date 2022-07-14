package co.com.sofka.zonatalentos.tourfranceapp.cyclist.usecases;

import co.com.sofka.zonatalentos.tourfranceapp.cyclist.collection.Cyclist;
import co.com.sofka.zonatalentos.tourfranceapp.cyclist.dto.CyclistDTO;
import co.com.sofka.zonatalentos.tourfranceapp.cyclist.mapper.CyclistMappers;
import co.com.sofka.zonatalentos.tourfranceapp.cyclist.repository.CyclistRepository;
import co.com.sofka.zonatalentos.tourfranceapp.cyclist.usecases.interfaces.SaveCyclist;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Service
@Validated
public class CreateCyclistUseCase implements SaveCyclist {
    private final CyclistRepository cyclistRepository;
    private final CyclistMappers mappers;

    public CreateCyclistUseCase(CyclistRepository cyclistRepository, CyclistMappers mappers) {
        this.cyclistRepository = cyclistRepository;
        this.mappers = mappers;
    }
    @Override
    public Mono<String> apply(CyclistDTO cyclistDTO) {
        return cyclistRepository
                .save(mappers.mapCyclistDTOToCyclist(null).apply(cyclistDTO))
                .map(Cyclist::getIdCyclist);
    }
}
