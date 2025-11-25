package s05.t01.blackjack_app.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import s05.t01.blackjack_app.model.entities.*;
import s05.t01.blackjack_app.repository.*;

@Service
public class GameService {

    private final SQLGameRepository sqlGameRepository;
    private final SQLPlayerRepository sqlPlayerRepository;

    public GameService(SQLGameRepository sqlGameRepository, SQLPlayerRepository sqlPlayerRepository, PlayerService playerService) {
        this.sqlGameRepository = sqlGameRepository;
        this.sqlPlayerRepository = sqlPlayerRepository;
    }

    public Mono<GameEntity> createGame(String playerName) {
        PlayerEntity player = new PlayerEntity();
        player.setPlayerName(playerName);
        player.setPlayerWins(0);
        player.setPlayerLosses(0);

        return sqlPlayerRepository.save(player)
                .flatMap(savedPlayer -> {
                    GameEntity game = new GameEntity();
                    game.setPlayerId(savedPlayer.getPlayerId());
                    game.setGameStatus(GameStatus.IN_PROGRESS);
                    return sqlGameRepository.save(game);
                });
    }

    public Mono<GameEntity> getGameById(Long gameId) {
        return sqlGameRepository.findById(gameId)
                .switchIfEmpty(Mono.error(new RuntimeException("Game with the ID " + gameId + " could not be found")));
    }

    public Mono<Void> deleteGame(Long gameId){
        return sqlGameRepository.deleteById(gameId)
                .switchIfEmpty(Mono.error(new RuntimeException("Game with the ID " + gameId + " could not be found")));
    }
}