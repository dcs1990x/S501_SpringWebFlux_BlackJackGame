package s05.t01.blackjack_app.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import s05.t01.blackjack_app.domain.entities.GameEntity;

public interface SQLGameRepository extends ReactiveCrudRepository<GameEntity, Long> {
    Flux<GameEntity> findAllByPlayerId(Long playerId);
}