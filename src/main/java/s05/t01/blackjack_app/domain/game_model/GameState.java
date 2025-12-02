package s05.t01.blackjack_app.domain.game_model;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@RequiredArgsConstructor
@Data
@Document(collection = "game_state")
public class GameState {

    @Id
    private String id;
    private Long gameId;
    private List<Card> playerCards;
    private List<Card> dealerCards;
    @NonNull private String playerName;
    @NonNull private Integer playerScore;
    @NonNull private Integer dealerScore;
    @NonNull private List<Card> deck;
    @NonNull private TurnType gamePhase;
    @NonNull private GameStatus gameStatus;
    @NonNull private GameResult result;
}