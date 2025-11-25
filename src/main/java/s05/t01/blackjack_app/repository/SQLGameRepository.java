package s05.t01.blackjack_app.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import s05.t01.blackjack_app.model.entities.GameEntity;

public interface SQLGameRepository extends ReactiveCrudRepository<GameEntity, Long> {}