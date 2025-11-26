package s05.t01.blackjack_app.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import s05.t01.blackjack_app.domain.entities.GameEntity;

public interface GameRepository extends ReactiveMongoRepository<GameEntity, Long> {
    Flux<GameEntity> findAllByPlayerId(Long playerId);
}