package s05.t01.blackjack_app.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;
import s05.t01.blackjack_app.model.entities.GameEntity;
import s05.t01.blackjack_app.model.entities.GameStatus;
import s05.t01.blackjack_app.model.entities.PlayerEntity;
import s05.t01.blackjack_app.repository.SQLGameRepository;
import s05.t01.blackjack_app.repository.SQLPlayerRepository;

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

    public Mono<GameEntity> getGame(Long id) {
        return sqlGameRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Game with the entered ID could not be found")));
    }

    public Mono<Void> deleteGame(Long id){
        return sqlGameRepository.deleteById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Game with the entered ID could not be found")));
    }
}