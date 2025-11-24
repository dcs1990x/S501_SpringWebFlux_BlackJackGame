package s05.t01.blackjack_app.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import s05.t01.blackjack_app.entities.mysql.SQLGameEntity;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GameResponseDTO {

    private Long gameId;
    private String playerName;
    private int playerScore;
    private int dealerScore;
    private LocalDate createdDate;
    private GameStatus gameStatus;
    private LocalDate finishedDate;
    private String winnerName;
    private List<CardDTO> playerHand;
    private List<CardDTO> dealerHand;

    public static GameResponseDTO fromEntity(SQLGameEntity sqlGameEntity) {
        return GameResponseDTO.builder()
                .gameId(sqlGameEntity.getGameId())
                .playerName(sqlGameEntity.getPlayerName())
                .playerScore(sqlGameEntity.getPlayerScore())
                .dealerScore(sqlGameEntity.getDealerScore())
                .createdDate(sqlGameEntity.getCreatedDate())
                .status(sqlGameEntity.getGameStatus())
                .finishedDate(sqlGameEntity.getFinishedDate())
                .winnerName(sqlGameEntity.getWinnerName())
                .playerHand(sqlGameEntity.getPlayerHand())
                .dealerHand(sqlGameEntity.getDealerHand())
                .build();
    }
}