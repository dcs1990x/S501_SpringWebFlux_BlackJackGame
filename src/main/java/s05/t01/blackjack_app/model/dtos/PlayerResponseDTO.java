package s05.t01.blackjack_app.model.dtos;

import lombok.Builder;
import lombok.Data;
import s05.t01.blackjack_app.entities.mysql.SQLPlayerEntity;

@Data
@Builder
public class PlayerResponseDTO{

    private Long id;
    private String name;
    private Integer gamesWon;
    private Integer gamesLost;

    public static PlayerResponseDTO fromEntity(SQLPlayerEntity SQLPlayerEntity) {
        return PlayerResponseDTO.builder()
                .id(SQLPlayerEntity.getPlayerId())
                .name(SQLPlayerEntity.getPlayerName())
                .gamesWon(SQLPlayerEntity.getPlayerWins())
                .gamesLost(SQLPlayerEntity.getPlayerLosses())
                .build();
    }
}