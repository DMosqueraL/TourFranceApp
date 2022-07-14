package co.com.sofka.zonatalentos.tourfranceapp.cyclist.routers;

import co.com.sofka.zonatalentos.tourfranceapp.cyclist.dto.CyclistDTO;
import co.com.sofka.zonatalentos.tourfranceapp.cyclist.usecases.ListCyclistsUseCase;
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
