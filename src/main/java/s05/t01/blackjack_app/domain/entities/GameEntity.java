package s05.t01.blackjack_app.domain.entities;

import jakarta.persistence.*;
import org.springframework.data.annotation.Transient;
import lombok.*;
import s05.t01.blackjack_app.domain.dtos.CardDTO;
import s05.t01.blackjack_app.domain.game_model.GameResult;
import s05.t01.blackjack_app.domain.game_model.GameStatus;
import s05.t01.blackjack_app.domain.game_model.GameTurnPhase;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "games")
public class GameEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Game ID")
    private Long gameId;

    @Column(name = "Player ID", nullable = false)
    private Long playerId;

    @Column(name = "Player Name", nullable = false)
    private String playerName;

    @Column(name = "Created at")
    private LocalDateTime createdDate;

    @Column(name = "Game Status", nullable = false)
    private GameStatus gameStatus;

    @Column(name = "Finished at")
    private LocalDateTime finishedDate;

    @Column(name = "Game Result")
    private GameResult gameResult;

    @Transient private GameTurnPhase gameTurnPhase;
    @Transient private int playerScore;
    @Transient private int dealerScore;
    @Transient private List<CardDTO> playerHand;
    @Transient private List<CardDTO> dealerHand;
}