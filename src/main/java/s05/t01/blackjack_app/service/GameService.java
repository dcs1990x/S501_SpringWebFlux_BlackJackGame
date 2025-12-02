package s05.t01.blackjack_app.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import s05.t01.blackjack_app.domain.dtos.*;
import s05.t01.blackjack_app.domain.game_model.*;
import s05.t01.blackjack_app.exceptions.*;
import s05.t01.blackjack_app.domain.entities.*;
import s05.t01.blackjack_app.repository.*;

@Service
public class GameService {

    private final GameRepository gameRepository;
    private final PlayerRepository playerRepository;
    private final GameEngine gameEngine;
    private final DTOEntityMapper dtoEntityMapper;

    public GameService(GameRepository gameRepository, PlayerRepository playerRepository, PlayerService playerService, GameEngine gameEngine, DTOEntityMapper dtoEntityMapper) {
        this.gameRepository = gameRepository;
        this.playerRepository = playerRepository;
        this.gameEngine = gameEngine;
        this.dtoEntityMapper = dtoEntityMapper;
    }

    public Mono<GameEntity> createGame(String playerName) {
        PlayerEntity player = new PlayerEntity();
        player.setPlayerName(playerName);
        player.setPlayerWins(0);
        player.setPlayerLosses(0);

        return playerRepository.save(player)
                .flatMap(savedPlayer -> {
                    GameState game = new GameState();
                    game.setPlayerId(savedPlayer.getPlayerId());
                    game.setGameStatus(GameStatus.IN_PROGRESS);
                    game.setTurnType(TurnType.PLAYER_TURN);
                    game.setGameResult(GameResult.ONGOING);
                    return gameRepository.save(game);
                });
    }

    public Mono<GameEntity> getGameById(Long gameId) {
        return gameRepository.findById(gameId)
                .switchIfEmpty(Mono.error(new GameNotFoundException(gameId)));
    }

    public Mono<GameResponseDTO> playHand(PlayRequestDTO playRequestDTO) {
        return gameRepository.findById(playRequestDTO.getGameId())
                .switchIfEmpty(Mono.error(new GameNotFoundException(playRequestDTO.getGameId())))
                .flatMap(game -> {
                    if (game.getGameStatus() != GameStatus.IN_PROGRESS) {
                        return Mono.error(new InvalidGameStateException());
                    }
                    return processPlayerAction(game, playRequestDTO.getAction());
                })
                .flatMap(this::evaluateGameState)
                .map();
    }

    private Mono<?> evaluateGameState(Object o) {
    }

    private Mono<?> processPlayerAction(GameEntity game, PlayType action) {
    }

    public Mono<Void> deleteGame(Long gameId){
        return gameRepository.deleteById(gameId)
                .switchIfEmpty(Mono.error(new GameNotFoundException(gameId)));
    }
}