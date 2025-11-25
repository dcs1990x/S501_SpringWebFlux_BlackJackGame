package s05.t01.blackjack_app.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import s05.t01.blackjack_app.model.entities.PlayerEntity;
import s05.t01.blackjack_app.model.dtos.DTOEntityMapper;
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

    public Flux<PlayerEntity> getAllPlayers() {
        return sqlPlayerRepository.findAll();
    }

    public Mono<PlayerEntity> getPlayerById(Long playerId) {
        PlayerEntity foundPlayer = sqlPlayerRepository.findById(playerId)
                .switchIfEmpty(Mono.error(new PlayerNotFoundException("Player with ID " + playerId + " could not be found.")));
        return Mono.just(foundPlayer);
    }

    public Mono<Void> deletePlayer(Long playerId) {
        return sqlPlayerRepository.existsById(playerId)
                .flatMap(exists -> exists ?
                        sqlPlayerRepository.deleteById(playerId) :
                        Mono.error(new PlayerNotFoundException("Player with ID " + playerId + " could not be found."))
                );
    }
}