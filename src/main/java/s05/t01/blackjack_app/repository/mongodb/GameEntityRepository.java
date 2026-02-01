package s05.t01.blackjack_app.repository.mongodb;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import s05.t01.blackjack_app.domain.entities.GameEntity;

@Repository
public interface GameEntityRepository extends ReactiveMongoRepository<GameEntity, String> {
    Flux<GameEntity> findAllByPlayerId(Long playerId);
    Mono<GameEntity> findByGameId(Long gameId);
    Mono<Void> deleteByGameId(Long gameId);
}