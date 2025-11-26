package s05.t01.blackjack_app.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import s05.t01.blackjack_app.domain.entities.GameEntity;
import s05.t01.blackjack_app.domain.entities.GameStatus;

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

    public static GameResponseDTO fromEntity(GameEntity gameEntity) {
        return GameResponseDTO.builder()
                .gameId(gameEntity.getGameId())
                .playerName(gameEntity.getPlayerName())
                .playerScore(gameEntity.getPlayerScore())
                .dealerScore(gameEntity.getDealerScore())
                .createdDate(gameEntity.getCreatedDate())
                .gameStatus(gameEntity.getGameStatus())
                .finishedDate(gameEntity.getFinishedDate())
                .winnerName(gameEntity.getWinnerName())
                .playerHand(gameEntity.getPlayerHand())
                .dealerHand(gameEntity.getDealerHand())
                .build();
    }
}