package s05.t01.blackjack_app.model.dtos;

import org.mapstruct.Mapper;
import s05.t01.blackjack_app.model.entities.GameEntity;
import s05.t01.blackjack_app.model.entities.PlayerEntity;
import s05.t01.blackjack_app.service.dtos.PlayerResponseDTO;

@Mapper(componentModel = "spring")
public interface DTOEntityMapper {

    GameEntity toEntity(CreateGameRequestDTO gameRequestDTO);
    PlayerEntity toEntity(CreatePlayerRequestDTO createPlayerRequestDTO);
    GameResponseDTO toDTO(GameEntity gameEntity);
    PlayerResponseDTO toDTO(PlayerEntity playerEntity);
}