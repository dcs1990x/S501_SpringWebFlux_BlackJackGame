package s05.t01.blackjack_app.repository;

import org.springframework.data.repository.CrudRepository;
import s05.t01.blackjack_app.model.entities.PlayerEntity;

public interface SQLPlayerRepository extends CrudRepository<PlayerEntity, Long> {}