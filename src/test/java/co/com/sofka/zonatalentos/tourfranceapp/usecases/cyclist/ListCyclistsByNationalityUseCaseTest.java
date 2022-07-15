package co.com.sofka.zonatalentos.tourfranceapp.usecases.cyclist;

import co.com.sofka.zonatalentos.tourfranceapp.cyclist.collection.Cyclist;
import co.com.sofka.zonatalentos.tourfranceapp.cyclist.mapper.CyclistMappers;
import co.com.sofka.zonatalentos.tourfranceapp.cyclist.repository.CyclistRepository;
import co.com.sofka.zonatalentos.tourfranceapp.cyclist.usecases.ListCyclistsByNationalityUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.*;


class ListCyclistsByNationalityUseCaseTest {

    @MockBean
    CyclistRepository cyclistRepository;

    @MockBean
    ListCyclistsByNationalityUseCase listCyclistsByNationalityUseCase;

    CyclistMappers mappers = new CyclistMappers();

    @BeforeEach
    void setUp() {
        cyclistRepository = mock(CyclistRepository.class);
        listCyclistsByNationalityUseCase = new ListCyclistsByNationalityUseCase(
                cyclistRepository, mappers
        );
    }

    @Test
    void listCyclistsByNationalityValidationTest() {

        var cyclist = new Cyclist();
        cyclist.setIdCyclist("XXXXXXXXXX");
        cyclist.setName("Doris Mosquera");
        cyclist.setNumCompetitor("001");
        cyclist.setNameTeam("Sofka");
        cyclist.setNationality("Colombia");

        var cyclistDTO = mappers.mapCyclistToCyclistDTO().apply(cyclist);

        when(cyclistRepository.save(cyclist)).thenReturn(Mono.just(cyclist));

        when(cyclistRepository.findCyclistsByNationality(cyclist.getNationality())).thenReturn(Flux.just(cyclist));

        StepVerifier.create(listCyclistsByNationalityUseCase
                .apply(cyclist.getNationality()))
                .expectSubscription()
                .expectNext(cyclistDTO)
                .verifyComplete();

        verify(cyclistRepository).findCyclistsByNameTeam(cyclist.getNationality());
    }
}