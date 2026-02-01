package s05.t01.blackjack_app.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import s05.t01.blackjack_app.exceptions.PlayerNotFoundException;
import s05.t01.blackjack_app.domain.dtos.PlayerResponseDTO;
import s05.t01.blackjack_app.domain.dtos.UpdatePlayerNameRequestDTO;
import s05.t01.blackjack_app.domain.dtos.DTOEntityMapper;
import s05.t01.blackjack_app.repository.mongodb.GameEntityRepository;
import s05.t01.blackjack_app.repository.r2dbc.PlayerRepository;

@Service
public class PlayerService {

    private final GameEntityRepository gameEntityRepository;
    private final PlayerRepository playerRepository;
    private final DTOEntityMapper dtoEntityMapper;

    public PlayerService(GameEntityRepository gameEntityRepository, PlayerRepository playerRepository, DTOEntityMapper dtoEntityMapper) {
        this.gameEntityRepository = gameEntityRepository;
        this.playerRepository = playerRepository;
        this.dtoEntityMapper = dtoEntityMapper;
    }

    public Flux<PlayerResponseDTO> getAllPlayers() {
        return playerRepository.findAll()
                .switchIfEmpty(Flux.error(new PlayerNotFoundException()))
                .map(dtoEntityMapper::toPlayerResponseDTO);
    }

    public Mono<PlayerResponseDTO> getPlayerById(Long playerId) {
        return playerRepository.findById(playerId)
                .switchIfEmpty(Mono.error(new PlayerNotFoundException(playerId)))
                .map(dtoEntityMapper::toPlayerResponseDTO);
    }

    public Mono<PlayerResponseDTO> updatePlayerName(Long playerId, UpdatePlayerNameRequestDTO updatePlayerNameRequestDTO) {
        return playerRepository.findById(playerId)
                .switchIfEmpty(Mono.error(new PlayerNotFoundException(playerId)))
                .flatMap(player -> {
                    player.setPlayerName(updatePlayerNameRequestDTO.getNewPlayerName());
                    return playerRepository.save(player)
                            .flatMap(savedPlayer ->
                                    gameEntityRepository.findAllByPlayerId(playerId)
                                            .flatMap(game -> {
                                                game.setPlayerName(savedPlayer.getPlayerName());
                                                return gameEntityRepository.save(game);
                                            })
                                            .then(Mono.just(savedPlayer))
                            );
                })
                .map(dtoEntityMapper::toPlayerResponseDTO);
    }

    public Mono<Void> deletePlayerById(Long playerId) {
        return playerRepository.existsById(playerId)
                .flatMap(exists -> exists ?
                        playerRepository.deleteById(playerId) :
                        Mono.error(new PlayerNotFoundException(playerId))
                );
    }
}