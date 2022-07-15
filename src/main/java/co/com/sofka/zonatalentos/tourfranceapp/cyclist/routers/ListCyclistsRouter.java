package co.com.sofka.zonatalentos.tourfranceapp.cyclist.routers;

import co.com.sofka.zonatalentos.tourfranceapp.cyclist.dto.CyclistDTO;
import co.com.sofka.zonatalentos.tourfranceapp.cyclist.usecases.ListCyclistsUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class ListCyclistsRouter {

    @Bean
    @RouterOperation(operation = @Operation(operationId = "get", summary = "- Get All Cyclist", tags = {"Cyclist"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful operation"),
                    @ApiResponse(responseCode = "400", description = "Invalid parameters in body supplied"),
                    @ApiResponse(responseCode = "404", description = "Cyclist not found")}))
    public RouterFunction<ServerResponse> listCyclists(ListCyclistsUseCase listCyclistsUseCase){
        return route(
                GET("/cyclist/list"),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(listCyclistsUseCase.get(),
                                CyclistDTO.class))
        );
    }
}
