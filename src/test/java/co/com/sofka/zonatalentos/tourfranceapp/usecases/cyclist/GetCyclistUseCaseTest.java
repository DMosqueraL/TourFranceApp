package co.com.sofka.zonatalentos.tourfranceapp.usecases.cyclist;

import co.com.sofka.zonatalentos.tourfranceapp.cyclist.collection.Cyclist;
import co.com.sofka.zonatalentos.tourfranceapp.cyclist.mapper.CyclistMappers;
import co.com.sofka.zonatalentos.tourfranceapp.cyclist.repository.CyclistRepository;
import co.com.sofka.zonatalentos.tourfranceapp.cyclist.usecases.GetCyclistUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.*;

class GetCyclistUseCaseTest {

    @MockBean
    CyclistRepository cyclistRepository;
    @MockBean
    GetCyclistUseCase getCyclistUseCase;
    CyclistMappers mappers = new CyclistMappers();

    @BeforeEach
    void setUp() {
        cyclistRepository = mock(CyclistRepository.class);
        getCyclistUseCase = new GetCyclistUseCase(cyclistRepository, mappers);
    }

    @Test
    void getCyclistValidationTest() {

        var cyclist = new Cyclist();
        cyclist.setIdCyclist("XXXXXXXXXX");
        cyclist.setName("Doris Mosquera");
        cyclist.setNumCompetitor("001");
        cyclist.setNameTeam("Sofka");
        cyclist.setNationality("Colombia");

        var cyclistDTO = mappers.mapCyclistToCyclistDTO().apply(cyclist);

        when(cyclistRepository.findById(cyclist.getIdCyclist())).thenReturn(Mono.just(cyclist));

        StepVerifier.create(getCyclistUseCase.apply(cyclist.getIdCyclist()))
                .expectSubscription()
                .expectNext(cyclistDTO)
                .verifyComplete();

        verify(cyclistRepository).findById(cyclist.getIdCyclist());
    }
}