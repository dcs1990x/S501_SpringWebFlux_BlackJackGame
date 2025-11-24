package s05.t01.blackjack_app.model.dtos;

import org.mapstruct.Mapper;
import s05.t01.blackjack_app.entities.mysql.SQLGameEntity;
import s05.t01.blackjack_app.entities.mysql.SQLPlayerEntity;
import s05.t01.blackjack_app.service.dtos.PlayerResponseDTO;

@Mapper(componentModel = "spring")
public interface DTOEntityMapper {

    SQLGameEntity toEntity(CreateGameRequestDTO gameRequestDTO);
    SQLPlayerEntity toEntity(CreatePlayerRequestDTO createPlayerRequestDTO);
    GameResponseDTO toDTO(SQLGameEntity sqlGameEntity);
    PlayerResponseDTO toDTO(SQLPlayerEntity sqlPlayerEntity);
}