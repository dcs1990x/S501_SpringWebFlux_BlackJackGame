package s05.t01.blackjack_app.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import s05.t01.blackjack_app.domain.entities.PlayerEntity;

@Repository
public interface PlayerRepository extends ReactiveCrudRepository<PlayerEntity, Long> {}
