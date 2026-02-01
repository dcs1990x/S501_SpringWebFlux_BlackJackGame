package s05.t01.blackjack_app.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import s05.t01.blackjack_app.domain.dtos.*;
import s05.t01.blackjack_app.domain.game_model.*;
import s05.t01.blackjack_app.exceptions.*;
import s05.t01.blackjack_app.domain.entities.*;
import s05.t01.blackjack_app.repository.mongodb.GameEntityRepository;
import s05.t01.blackjack_app.repository.mongodb.GameStateRepository;
import s05.t01.blackjack_app.repository.r2dbc.PlayerRepository;

@Service
public class GameService {

    private final GameEntityRepository gameEntityRepository;
    private final GameStateRepository gameStateRepository;
    private final PlayerRepository playerRepository;
    private final GameSyncService gameSyncService;
    private final GameEngine gameEngine;
    private final DTOEntityMapper dtoEntityMapper;

    public GameService(GameEntityRepository gameEntityRepository, PlayerRepository playerRepository, PlayerService playerService, GameStateRepository gameStateRepository, GameSyncService gameSyncService, GameEngine gameEngine, DTOEntityMapper dtoEntityMapper) {
        this.gameEntityRepository = gameEntityRepository;
        this.playerRepository = playerRepository;
        this.gameStateRepository = gameStateRepository;
        this.gameSyncService = gameSyncService;
        this.gameEngine = gameEngine;
        this.dtoEntityMapper = dtoEntityMapper;
    }

    public Mono<GameEntity> createGame(String playerName) {
        PlayerEntity newPlayer = new PlayerEntity();
        newPlayer.setPlayerName(playerName);
        newPlayer.setPlayerWins(0);
        newPlayer.setPlayerLosses(0);

        return playerRepository.save(newPlayer)
                .flatMap(savedPlayer -> {
                    GameState gameState = gameEngine.initializeGame(savedPlayer);
                    return gameStateRepository.save(gameState)
                            .flatMap(gameSyncService::syncState);
                });
    }

    public Flux<GameResponseDTO> getAllGames() {
        return gameEntityRepository.findAll()
                .switchIfEmpty(Flux.error(new GameNotFoundException()))
                .map(dtoEntityMapper::toGameResponseDTO);
    }

    public Mono<GameEntity> getGameById(Long gameId) {
        return gameEntityRepository.findByGameId(gameId)
                .switchIfEmpty(Mono.error(new GameNotFoundException(gameId)));
    }

    public Mono<GameResponseDTO> playHand(PlayRequestDTO playRequestDTO) {
        return gameStateRepository.findByGameId(playRequestDTO.getGameId())
                .switchIfEmpty(Mono.error(new GameNotFoundException(playRequestDTO.getGameId())))
                .flatMap(gameState -> {
                    if (!gameState.getGameStatus().isFinalState()) {
                        return processPlayerAction(gameState, playRequestDTO.getAction());
                    } else {
                        return Mono.error(new InvalidGameStateException());
                    }
                })
                .flatMap(this::evaluateGameState)
                .flatMap(gameStateRepository::save)
                .flatMap(gameSyncService::syncState)
                .map(dtoEntityMapper::toGameResponseDTO);
    }

    private Mono<GameState> evaluateGameState(GameState gameState) {
        gameEngine.decideWinner(gameState);
        return Mono.just(gameState);
    }

    private Mono<GameState> processPlayerAction(GameState gameState, PlayType action) {
        switch (action) {
            case HIT -> gameEngine.chooseHit(gameState);
            case STAND -> gameEngine.chooseStand(gameState);
            default -> {
                return Mono.error(new InvalidActionException());
            }
        }
        return Mono.just(gameState);
    }

    public Mono<Void> deleteGame(Long gameId){
        gameStateRepository.deleteByGameId(gameId)
                .switchIfEmpty(Mono.error(new GameNotFoundException(gameId)));
        return gameEntityRepository.deleteByGameId(gameId)
                .switchIfEmpty(Mono.error(new GameNotFoundException(gameId)));
    }
}