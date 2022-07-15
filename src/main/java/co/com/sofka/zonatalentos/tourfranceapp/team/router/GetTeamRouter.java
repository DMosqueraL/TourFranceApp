package co.com.sofka.zonatalentos.tourfranceapp.team.router;

import co.com.sofka.zonatalentos.tourfranceapp.team.dto.TeamDTO;
import co.com.sofka.zonatalentos.tourfranceapp.team.usecases.GetTeamUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class GetTeamRouter {

    @Bean
    public RouterFunction<ServerResponse> getTeam(GetTeamUseCase getTeamUseCase){
        return route(
                GET("/team/get/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(getTeamUseCase
                                .apply(request.pathVariable("id")), TeamDTO.class))
        );
    }
}
