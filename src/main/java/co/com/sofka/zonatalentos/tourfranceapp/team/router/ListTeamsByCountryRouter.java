package co.com.sofka.zonatalentos.tourfranceapp.team.router;

import co.com.sofka.zonatalentos.tourfranceapp.team.dto.TeamDTO;
import co.com.sofka.zonatalentos.tourfranceapp.team.usecases.ListTeamsByCountryUseCase;
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

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class ListTeamsByCountryRouter {

    @Bean
    @RouterOperation(operation = @Operation(operationId = "apply", summary = "- Get Teams by Country", tags = {"Team"},
            parameters = {@Parameter(in = ParameterIn.PATH, name = "country", description = "Get Teams by Country")},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful operation"),
                    @ApiResponse(responseCode = "400", description = "Invalid parameters in body supplied"),
                    @ApiResponse(responseCode = "404", description = "Team not found")}))
    public RouterFunction<ServerResponse> listTeamsByCountry(ListTeamsByCountryUseCase listTeamsByCountryUseCase){
        return route(
                GET("teams/{country}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(listTeamsByCountryUseCase
                                .apply(request.pathVariable("country")),
                                TeamDTO.class))
        );
    }
}
