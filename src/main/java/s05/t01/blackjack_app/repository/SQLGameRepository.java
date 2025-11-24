package s05.t01.blackjack_app.repository;

import org.springframework.data.repository.CrudRepository;
import s05.t01.blackjack_app.entities.mysql.SQLGameEntity;

public interface SQLGameRepository extends CrudRepository<SQLGameEntity, Long> {}