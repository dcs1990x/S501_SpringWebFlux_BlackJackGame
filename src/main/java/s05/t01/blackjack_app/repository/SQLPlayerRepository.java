package s05.t01.blackjack_app.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import s05.t01.blackjack_app.model.entities.PlayerEntity;

@Repository
public interface SQLPlayerRepository extends ReactiveCrudRepository<PlayerEntity, Long> {}