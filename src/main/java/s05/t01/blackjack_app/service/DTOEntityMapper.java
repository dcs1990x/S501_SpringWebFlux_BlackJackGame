package s05.t01.blackjack_app.service;

import org.mapstruct.Mapper;
import s05.t01.blackjack_app.entities.mysql.SQLGameEntity;
import s05.t01.blackjack_app.entities.mysql.SQLPlayerEntity;
import s05.t01.blackjack_app.service.dtos.GameRequestDTO;
import s05.t01.blackjack_app.service.dtos.GameResponseDTO;
import s05.t01.blackjack_app.service.dtos.PlayerRequestDTO;
import s05.t01.blackjack_app.service.dtos.PlayerResponseDTO;

@Mapper(componentModel = "spring")
public interface DTOEntityMapper {

    SQLGameEntity toEntity(GameRequestDTO gameRequestDTO);
    SQLPlayerEntity toEntity(PlayerRequestDTO playerRequestDTO);
    GameResponseDTO toDTO(SQLGameEntity sqlGameEntity);
    PlayerResponseDTO toDTO(SQLPlayerEntity sqlPlayerEntity);
}