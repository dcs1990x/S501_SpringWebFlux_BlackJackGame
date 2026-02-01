package s05.t01.blackjack_app.repository.mongodb;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import s05.t01.blackjack_app.domain.game_model.GameState;

@Repository
public interface GameStateRepository extends ReactiveMongoRepository <GameState, String>{
    Mono<GameState> findByGameId(Long gameId);
    Mono<Void> deleteByGameId(Long gameId);
}