package co.com.sofka.zonatalentos.tourfranceapp.team.repository;


import co.com.sofka.zonatalentos.tourfranceapp.team.collection.Team;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface TeamRepository extends ReactiveMongoRepository<Team, String> {
    Mono<Team> findTeamByCodeTeam(String codeTeam);
    Flux<Team> findAll();
    Flux<Team> findTeamsByPartnerCountry(String country);

}
