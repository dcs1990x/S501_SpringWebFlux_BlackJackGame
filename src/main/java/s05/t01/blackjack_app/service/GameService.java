package s05.t01.blackjack_app.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import s05.t01.blackjack_app.domain.dtos.GameResponseDTO;
import s05.t01.blackjack_app.domain.dtos.PlayRequestDTO;
import s05.t01.blackjack_app.domain.game_model.GameResult;
import s05.t01.blackjack_app.domain.game_model.GameStatus;
import s05.t01.blackjack_app.domain.game_model.GameTurnPhase;
import s05.t01.blackjack_app.exceptions.GameNotFoundException;
import s05.t01.blackjack_app.domain.entities.*;
import s05.t01.blackjack_app.repository.*;
import java.time.LocalDateTime;

@Service
public class GameService {

    private final GameRepository gameRepository;
    private final PlayerRepository playerRepository;

    public GameService(GameRepository gameRepository, PlayerRepository playerRepository, PlayerService playerService) {
        this.gameRepository = gameRepository;
        this.playerRepository = playerRepository;
    }

    public Mono<GameEntity> createGame(String playerName) {
        PlayerEntity player = new PlayerEntity();
        player.setPlayerName(playerName);
        player.setPlayerWins(0);
        player.setPlayerLosses(0);

        return playerRepository.save(player)
                .flatMap(savedPlayer -> {
                    GameEntity game = new GameEntity();
                    game.setPlayerId(savedPlayer.getPlayerId());
                    game.setGameStatus(GameStatus.IN_PROGRESS);
                    game.setGameTurnPhase(GameTurnPhase.SETUP);
                    game.setGameResult(GameResult.ONGOING);
                    game.setCreatedDate(LocalDateTime.now());
                    return gameRepository.save(game);
                });
    }

    public Mono<GameEntity> getGameById(Long gameId) {
        return gameRepository.findById(gameId)
                .switchIfEmpty(Mono.error(new GameNotFoundException(gameId)));
    }

    public Mono<GameResponseDTO> playHand(PlayRequestDTO playRequestDTO){
        return;
    }

    public Mono<Void> deleteGame(Long gameId){
        return gameRepository.deleteById(gameId)
                .switchIfEmpty(Mono.error(new GameNotFoundException(gameId)));
    }
}