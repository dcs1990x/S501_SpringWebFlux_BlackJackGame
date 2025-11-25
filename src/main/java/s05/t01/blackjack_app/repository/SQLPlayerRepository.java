package s05.t01.blackjack_app.repository;

import org.springframework.stereotype.Repository;

@Repository
public interface SQLPlayerRepository extends ReactiveCrudRepository<PlayerEntity, Long> {}