package co.com.sofka.zonatalentos.tourfranceapp.usecases.cyclist;

import co.com.sofka.zonatalentos.tourfranceapp.team.collection.Team;
import co.com.sofka.zonatalentos.tourfranceapp.team.dto.TeamDTO;
import co.com.sofka.zonatalentos.tourfranceapp.team.mapper.TeamMappers;
import co.com.sofka.zonatalentos.tourfranceapp.team.repository.TeamRepository;
import co.com.sofka.zonatalentos.tourfranceapp.team.router.CreateTeamRouter;
import co.com.sofka.zonatalentos.tourfranceapp.team.usecases.CreateTeamUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

@WebFluxTest
@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = {CreateTeamRouter.class, CreateTeamUseCase.class, TeamMappers.class})
class CreateTeamRouterTest {

    @MockBean
    private TeamRepository teamRepository;
    @Autowired
    private WebTestClient webTestClient;

    @Test
    void usuarioRutasTest() {

        var team = new Team();
        team.setIdTeam("X-XXXXX");
        team.setNameTeam("Sofka");
        team.setCodeTeam("001");
        team.setPartnerCountry("Colombia");

        var teamDTO = new TeamDTO();
        teamDTO.setIdTeam(team.getIdTeam());
        teamDTO.setNameTeam(team.getNameTeam());
        teamDTO.setCodeTeam(team.getCodeTeam());
        teamDTO.setPartnerCountry(team.getPartnerCountry());

        Mockito.when(teamRepository.save(Mockito.any(Team.class))).thenReturn(Mono.just(team));

        webTestClient.post()
                .uri("/team/create")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(teamDTO), TeamDTO.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody(TeamDTO.class)
                .value(response -> {
                    Assertions.assertEquals(response.getIdTeam(), team.getIdTeam());
                    Assertions.assertEquals(response.getNameTeam(), team.getNameTeam());
                    Assertions.assertEquals(response.getCodeTeam(), team.getCodeTeam());
                    Assertions.assertEquals(response.getPartnerCountry(), team.getPartnerCountry());
                });
        Mockito.verify(teamRepository).save(Mockito.any(Team.class));
    }
}