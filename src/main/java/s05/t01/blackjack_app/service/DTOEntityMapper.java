package s05.t01.blackjack_app.service;

import org.mapstruct.Mapper;
import s05.t01.blackjack_app.entities.mysql.SQLGameEntity;
import s05.t01.blackjack_app.entities.mysql.SQLPlayerEntity;
import s05.t01.blackjack_app.service.dtos.SQLGameRequestDTO;
import s05.t01.blackjack_app.service.dtos.SQLGameResponseDTO;
import s05.t01.blackjack_app.service.dtos.SQLPlayerRequestDTO;
import s05.t01.blackjack_app.service.dtos.SQLPlayerResponseDTO;

@Mapper(componentModel = "spring")
public interface DTOEntityMapper {

    SQLGameEntity toEntity(SQLGameRequestDTO sqlGameRequestDTO);
    SQLPlayerEntity toEntity(SQLPlayerRequestDTO sqlPlayerRequestDTO);
    SQLGameResponseDTO toDTO(SQLGameEntity sqlGameEntity);
    SQLPlayerResponseDTO toDTO(SQLPlayerEntity sqlPlayerEntity);
}