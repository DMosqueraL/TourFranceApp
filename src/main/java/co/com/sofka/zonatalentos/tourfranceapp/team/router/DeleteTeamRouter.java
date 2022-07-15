package co.com.sofka.zonatalentos.tourfranceapp.team.router;

import co.com.sofka.zonatalentos.tourfranceapp.team.usecases.DeleteTeamUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class DeleteTeamRouter {

    @Bean
    @RouterOperation(operation = @Operation(operationId = "delete", summary = "- Delete Team by Id", tags = {"Team"},
            parameters = {@Parameter(in = ParameterIn.PATH, name = "id", description = "Delete Team by Id")},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful operation"),
                    @ApiResponse(responseCode = "400", description = "Invalid parameters in body supplied"),
                    @ApiResponse(responseCode = "404", description = "Team not found")}))
    public RouterFunction<ServerResponse> deleteTeam(DeleteTeamUseCase deleteTeamUseCase){
        return route(
                DELETE("/team/delete/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.accepted()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(deleteTeamUseCase
                                .apply(request.pathVariable("id")), void.class))

        );
    }
}
