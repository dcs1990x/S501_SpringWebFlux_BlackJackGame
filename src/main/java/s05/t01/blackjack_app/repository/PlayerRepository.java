package s05.t01.blackjack_app.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import s05.t01.blackjack_app.domain.entities.PlayerEntity;

public interface PlayerRepository extends ReactiveCrudRepository<PlayerEntity, Long> {}
