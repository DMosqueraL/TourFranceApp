package co.com.sofka.zonatalentos.tourfranceapp.cyclist.routers;

import co.com.sofka.zonatalentos.tourfranceapp.cyclist.usecases.DeleteCyclistUseCase;
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
public class DeleteCyclistRouter {
    @Bean
    @RouterOperation(operation = @Operation(operationId = "delete", summary = "- Delete Cyclist by Id", tags = {"Cyclist"},
            parameters = {@Parameter(in = ParameterIn.PATH, name = "id", description = "Delete Cyclist by Id")},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful operation"),
                    @ApiResponse(responseCode = "400", description = "Invalid parameters in body supplied"),
                    @ApiResponse(responseCode = "404", description = "Cyclist not found")}))
    public RouterFunction<ServerResponse> deleteCyclist(DeleteCyclistUseCase deleteCyclistUseCase){
        return route(
                DELETE("/cyclist/delete/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.accepted()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(deleteCyclistUseCase
                                .apply(request.pathVariable("id")), void.class))
        );
    }
}
