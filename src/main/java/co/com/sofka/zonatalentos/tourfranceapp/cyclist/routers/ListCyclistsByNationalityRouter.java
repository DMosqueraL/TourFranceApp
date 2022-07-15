package co.com.sofka.zonatalentos.tourfranceapp.cyclist.routers;


import co.com.sofka.zonatalentos.tourfranceapp.cyclist.dto.CyclistDTO;
import co.com.sofka.zonatalentos.tourfranceapp.cyclist.usecases.ListCyclistsByNationalityUseCase;
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
public class ListCyclistsByNationalityRouter {
    @Bean
    public RouterFunction<ServerResponse> listCyclistsByNationality(ListCyclistsByNationalityUseCase listCyclistsByNationalityUseCase){
        return route(
                GET("/cyclist/list/{nationality}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(listCyclistsByNationalityUseCase
                                        .apply(request.pathVariable("nationality")),
                                CyclistDTO.class))
        );
    }
}
