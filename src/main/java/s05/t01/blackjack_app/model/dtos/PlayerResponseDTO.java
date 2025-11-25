package s05.t01.blackjack_app.model.dtos;

import lombok.Builder;
import lombok.Data;
import s05.t01.blackjack_app.model.entities.PlayerEntity;

@Data
@Builder
public class PlayerResponseDTO{

    private Long id;
    private String name;
    private Integer playerWins;
    private Integer playerLosses;

    public static PlayerResponseDTO fromEntity(PlayerEntity PlayerEntity) {
        return PlayerResponseDTO.builder()
                .id(PlayerEntity.getPlayerId())
                .name(PlayerEntity.getPlayerName())
                .playerWins(PlayerEntity.getPlayerWins())
                .playerLosses(PlayerEntity.getPlayerLosses())
                .build();
    }
}