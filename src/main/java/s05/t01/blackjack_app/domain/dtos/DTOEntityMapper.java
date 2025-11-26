package s05.t01.blackjack_app.domain.dtos;

import org.mapstruct.Mapper;
import s05.t01.blackjack_app.domain.entities.GameEntity;
import s05.t01.blackjack_app.domain.entities.PlayerEntity;

@Mapper(componentModel = "spring")
public interface DTOEntityMapper {

    GameEntity toGameEntity(CreateGameRequestDTO gameRequestDTO);
    GameEntity toEntity(GameResponseDTO gameResponseDTO);
    PlayerEntity toPlayerEntity(CreateGameRequestDTO createGameRequestDTO);
    GameResponseDTO toGameResponseDTO(GameEntity gameEntity);
    PlayerResponseDTO toPlayerResponseDTO(PlayerEntity playerEntity);
}
