package s05.t01.blackjack_app.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;
import s05.t01.blackjack_app.domain.game_model.GameState;

public interface GameStateRepository extends ReactiveMongoRepository <GameState, String>{
    Mono<GameState> findByGameId(Long gameId);
    Mono<Void> deleteByGameId(Long gameId);
}