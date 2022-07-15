package co.com.sofka.zonatalentos.tourfranceapp.cyclist.routers;

import co.com.sofka.zonatalentos.tourfranceapp.cyclist.dto.CyclistDTO;
import co.com.sofka.zonatalentos.tourfranceapp.cyclist.usecases.ListCyclistsByNameTeamUseCase;
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
public class ListCyclistsByNameTeamRouter {

    @Bean
    @RouterOperation(operation = @Operation(operationId = "apply", summary = "- Get Cyclist by NameTeam", tags = {"Cyclist"},
            parameters = {@Parameter(in = ParameterIn.PATH, name = "nameTeam", description = "Get Cyclist by NameTeam")},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful operation"),
                    @ApiResponse(responseCode = "400", description = "Invalid parameters in body supplied"),
                    @ApiResponse(responseCode = "404", description = "Cyclist not found")}))
    public RouterFunction<ServerResponse> listCyclistsByNameTeam(ListCyclistsByNameTeamUseCase listCyclistsByNameTeamUseCase){
        return route(
                GET("/cyclist/list/{nameTeam}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(listCyclistsByNameTeamUseCase
                                .apply(request.pathVariable("nameTeam")),
                                CyclistDTO.class))
        );
    }
}
