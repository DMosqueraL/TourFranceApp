package co.com.sofka.zonatalentos.tourfranceapp.team.router;

import co.com.sofka.zonatalentos.tourfranceapp.team.dto.TeamDTO;
import co.com.sofka.zonatalentos.tourfranceapp.team.usecases.CreateTeamUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.function.Function;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class CreateTeamRouter {

    @Bean
    @RouterOperation(beanClass = CreateTeamUseCase.class, beanMethod = "apply", operation = @Operation(
            operationId = "createTeam", summary = "- Create Team", tags = {
            "Team"}, responses = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "Invalid parameters in body supplied"),
            @ApiResponse(responseCode = "404", description = "Team not found")}))
    public RouterFunction<ServerResponse> createTeam(CreateTeamUseCase createTeamUseCase){
        Function<TeamDTO, Mono<ServerResponse>> createTeam = teamDTO ->
                createTeamUseCase.apply(teamDTO)
                        .flatMap(result -> ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(result));

        return route(
                POST("/team/create").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(TeamDTO.class).flatMap(createTeam)
        );
    }
}
