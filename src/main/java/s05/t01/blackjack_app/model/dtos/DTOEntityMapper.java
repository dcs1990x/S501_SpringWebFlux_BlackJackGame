package s05.t01.blackjack_app.model.dtos;

import org.mapstruct.Mapper;
import reactor.core.publisher.Mono;
import s05.t01.blackjack_app.model.entities.GameEntity;
import s05.t01.blackjack_app.model.entities.PlayerEntity;

@Mapper(componentModel = "spring")
public interface DTOEntityMapper {

    GameEntity toGameEntity(CreateGameRequestDTO gameRequestDTO);
    GameEntity toEntity(GameResponseDTO gameResponseDTO);
    PlayerEntity toPlayerEntity(CreateGameRequestDTO createGameRequestDTO);
    GameResponseDTO toDTO(Mono<GameEntity> gameEntity);
    PlayerResponseDTO toPlayerResponseDTO(PlayerEntity playerEntity);
}