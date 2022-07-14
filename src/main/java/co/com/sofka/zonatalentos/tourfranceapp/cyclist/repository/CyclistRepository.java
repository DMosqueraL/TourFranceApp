package co.com.sofka.zonatalentos.tourfranceapp.cyclist.repository;

import co.com.sofka.zonatalentos.tourfranceapp.cyclist.collection.Cyclist;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface CyclistRepository extends ReactiveMongoRepository<Cyclist, String> {
    Mono<Cyclist> findCyclistByNumCompetitor(String number);
    Flux<Cyclist> findAll();
    Flux<Cyclist> findCyclistsByNationality(String nationality);
    Flux<Cyclist> findCyclistsByNameTeam(String nameTeam);
}
