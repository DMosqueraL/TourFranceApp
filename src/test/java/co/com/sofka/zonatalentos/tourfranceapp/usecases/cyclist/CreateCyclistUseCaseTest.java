package co.com.sofka.zonatalentos.tourfranceapp.usecases.cyclist;

import co.com.sofka.zonatalentos.tourfranceapp.cyclist.collection.Cyclist;
import co.com.sofka.zonatalentos.tourfranceapp.cyclist.dto.CyclistDTO;
import co.com.sofka.zonatalentos.tourfranceapp.cyclist.mapper.CyclistMappers;
import co.com.sofka.zonatalentos.tourfranceapp.cyclist.repository.CyclistRepository;
import co.com.sofka.zonatalentos.tourfranceapp.cyclist.usecases.CreateCyclistUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class CreateCyclistUseCaseTest {

    @MockBean
    CyclistRepository cyclistRepository;
    @MockBean
    CreateCyclistUseCase createCyclistUseCase;
    CyclistMappers mappers = new CyclistMappers();

    @BeforeEach
    void setUp() {
        cyclistRepository = mock(CyclistRepository.class);
        createCyclistUseCase = new CreateCyclistUseCase(cyclistRepository, mappers);
    }

    @Test
    void createCyclistValidationTest() {

        var cyclist = new Cyclist();
        cyclist.setIdCyclist("XXXXXXXXXX");
        cyclist.setName("Doris Mosquera");
        cyclist.setNumCompetitor("001");
        cyclist.setNameTeam("Sofka");
        cyclist.setNationality("Colombia");

        var cyclistDTO = mappers.mapCyclistToCyclistDTO().apply(cyclist);

        when(cyclistRepository.save(any(Cyclist.class))).thenReturn(Mono.just(cyclist));

        StepVerifier.create(createCyclistUseCase.apply(cyclistDTO))
                .expectSubscription()
                .expectNext(cyclistDTO)
                .verifyComplete();

        verify(cyclistRepository).save(any(Cyclist.class));
    }
}