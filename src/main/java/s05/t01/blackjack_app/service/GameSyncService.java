package s05.t01.blackjack_app.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import s05.t01.blackjack_app.domain.entities.GameEntity;
import s05.t01.blackjack_app.domain.game_model.GameState;
import s05.t01.blackjack_app.repository.mongodb.GameEntityRepository;
import java.time.Instant;

@Service
@RequiredArgsConstructor
public class GameSyncService {

    private final GameEntityRepository gameEntityRepository;

    public Mono<GameEntity> syncState(GameState gameState) {
        return gameEntityRepository.findByGameId(gameState.getGameId())
                .defaultIfEmpty(buildNewEntity(gameState))
                .flatMap(existingEntity -> updateEntity(existingEntity, gameState))
                .flatMap(gameEntityRepository::save);
    }

    private GameEntity buildNewEntity(GameState state) {
        return GameEntity.builder()
                .gameId(state.getGameId())
                .playerId(state.getPlayerId())
                .playerName(state.getPlayerName())
                .createdDate(Instant.now())
                .gameStatus(state.getGameStatus())
                .gamePhase(state.getGamePhase())
                .gameResult(state.getGameResult())
                .build();
    }

    private Mono<GameEntity> updateEntity(GameEntity entity, GameState state) {
        entity.setGameStatus(state.getGameStatus());
        entity.setGamePhase(state.getGamePhase());
        entity.setGameResult(state.getGameResult());

        if (!state.getGameStatus().isFinalState()) {
            if (entity.getFinishedDate() == null) {
                entity.setFinishedDate(Instant.now());
            }
        }
        return Mono.just(entity);
    }
}