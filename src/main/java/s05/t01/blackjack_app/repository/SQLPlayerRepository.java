package s05.t01.blackjack_app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import s05.t01.blackjack_app.entities.mysql.SQLPlayerEntity;

@Repository
public interface SQLPlayerRepository extends CrudRepository<SQLPlayerEntity, Long> {


}