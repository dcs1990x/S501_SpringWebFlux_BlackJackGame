package s05.t01.blackjack_app.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import s05.t01.blackjack_app.domain.entities.*;
import s05.t01.blackjack_app.exceptions.PlayerNotFoundException;
import s05.t01.blackjack_app.domain.dtos.PlayerResponseDTO;
import s05.t01.blackjack_app.domain.dtos.UpdatePlayerNameRequestDTO;
import s05.t01.blackjack_app.domain.dtos.DTOEntityMapper;
import s05.t01.blackjack_app.repository.*;

@Service
public class PlayerService {

    private final SQLGameRepository sqlGameRepository;
    private final SQLPlayerRepository sqlPlayerRepository;
    private final DTOEntityMapper dtoEntityMapper;

    public PlayerService(SQLGameRepository sqlGameRepository, SQLPlayerRepository sqlPlayerRepository, DTOEntityMapper dtoEntityMapper) {
        this.sqlGameRepository = sqlGameRepository;
        this.sqlPlayerRepository = sqlPlayerRepository;
        this.dtoEntityMapper = dtoEntityMapper;
    }

    public Flux<PlayerResponseDTO> getAllPlayers() {
        return sqlPlayerRepository.findAll()
                .switchIfEmpty(Flux.error(new PlayerNotFoundException()))
                .map(dtoEntityMapper::toPlayerResponseDTO);
    }

    public Mono<PlayerResponseDTO> getPlayerById(Long playerId) {
        return sqlPlayerRepository.findById(playerId)
                .switchIfEmpty(Mono.error(new PlayerNotFoundException(playerId)))
                .map(dtoEntityMapper::toPlayerResponseDTO);
    }

    public Mono<PlayerResponseDTO> updatePlayerName(Long playerId, UpdatePlayerNameRequestDTO updatePlayerNameRequestDTO) {
        return sqlPlayerRepository.findById(playerId)
                .switchIfEmpty(Mono.error(new PlayerNotFoundException(playerId)))
                .flatMap(player -> {
                    player.setPlayerName(updatePlayerNameRequestDTO.getNewPlayerName());
                    return sqlPlayerRepository.save(player)
                            .flatMap(savedPlayer ->
                                    sqlGameRepository.findAllByPlayerId(playerId)
                                            .flatMap(game -> {
                                                game.setPlayerName(savedPlayer.getPlayerName());
                                                return sqlGameRepository.save(game);
                                            })
                                            .then(Mono.just(savedPlayer))
                            );
                })
                .map(dtoEntityMapper::toPlayerResponseDTO);
    }


    public Mono<Void> deletePlayer(Long playerId) {
        return sqlPlayerRepository.existsById(playerId)
                .flatMap(exists -> exists ?
                        sqlPlayerRepository.deleteById(playerId) :
                        Mono.error(new PlayerNotFoundException(playerId))
                );
    }
}