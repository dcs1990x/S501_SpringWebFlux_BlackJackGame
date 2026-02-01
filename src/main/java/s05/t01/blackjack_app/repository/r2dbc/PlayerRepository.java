package s05.t01.blackjack_app.repository.r2dbc;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import s05.t01.blackjack_app.domain.entities.PlayerEntity;

@Repository
public interface PlayerRepository extends R2dbcRepository<PlayerEntity, Long> {}
