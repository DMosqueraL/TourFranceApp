package co.com.sofka.zonatalentos.tourfranceapp.usecases.cyclist;

import co.com.sofka.zonatalentos.tourfranceapp.cyclist.collection.Cyclist;
import co.com.sofka.zonatalentos.tourfranceapp.cyclist.mapper.CyclistMappers;
import co.com.sofka.zonatalentos.tourfranceapp.cyclist.repository.CyclistRepository;
import co.com.sofka.zonatalentos.tourfranceapp.cyclist.usecases.DeleteCyclistUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DeleteCyclistUseCaseTest {

    @MockBean
    CyclistRepository cyclistRepository;

    @MockBean
    DeleteCyclistUseCase deleteCyclistUseCase;

    CyclistMappers mappers = new CyclistMappers();

    @BeforeEach
    void setUp() {
        cyclistRepository = mock(CyclistRepository.class);
        deleteCyclistUseCase = new DeleteCyclistUseCase(cyclistRepository);
    }

    @Test
    void deleteCyclistValidationTest(){
        var cyclist = new Cyclist();
        cyclist.setIdCyclist("XXXXXXXXXX");
        cyclist.setName("Doris Mosquera");
        cyclist.setNumCompetitor("001");
        cyclist.setNameTeam("Sofka");
        cyclist.setNationality("Colombia");

        Mono.just(cyclist).flatMap(cyclistRepository::save).subscribe();

        when(cyclistRepository.deleteById(cyclist.getIdCyclist())).thenReturn(Mono.empty());

        StepVerifier.create(deleteCyclistUseCase.apply(cyclist.getIdCyclist()))
                .expectSubscription()
                .verifyComplete();

        verify(cyclistRepository).deleteById(cyclist.getIdCyclist());
    }
}