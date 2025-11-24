package s05.t01.blackjack_app.repository;

import org.springframework.data.repository.CrudRepository;
import s05.t01.blackjack_app.model.entities.GameEntity;

public interface SQLGameRepository extends CrudRepository<GameEntity, Long> {}