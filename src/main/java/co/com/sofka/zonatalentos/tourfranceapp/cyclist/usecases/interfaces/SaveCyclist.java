package co.com.sofka.zonatalentos.tourfranceapp.cyclist.usecases.interfaces;

import co.com.sofka.zonatalentos.tourfranceapp.cyclist.dto.CyclistDTO;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@FunctionalInterface
public interface SaveCyclist {
    Mono<String> apply(@Valid CyclistDTO cyclistDTO);
}
