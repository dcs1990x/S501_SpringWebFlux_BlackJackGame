package s05.t01.blackjack_app.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import s05.t01.blackjack_app.domain.game_model.GameState;
import s05.t01.blackjack_app.domain.game_model.GameStatus;
import java.time.LocalDate;
import java.time.ZoneId;
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

    public static GameResponseDTO fromState(GameState state) {
        return GameResponseDTO.builder()
                .gameId(state.getGameId())
                .playerName(state.getPlayerName())
                .playerScore(state.getPlayerScore())
                .dealerScore(state.getDealerScore())
                .createdDate(state.getCreatedDate().atZone(ZoneId.systemDefault()).toLocalDate())
                .gameStatus(state.getGameStatus())
                .finishedDate(state.getFinishedDate() != null
                        ? state.getFinishedDate().atZone(ZoneId.systemDefault()).toLocalDate()
                        : null)
                .winnerName(state.getGameResult() != null ? state.getGameResult().name() : null)
                .playerHand(CardDTO.fromCards(state.getPlayerCards()))
                .dealerHand(CardDTO.fromCards(state.getDealerCards()))
                .build();
    }
}