package s05.t01.blackjack_app.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import s05.t01.blackjack_app.model.dtos.CardDTO;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "games")
public class GameEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Game ID")
    private Long gameId;

    @Column(name = "Player Name", nullable = false)
    private String playerName;

    @Column(name = "Created at")
    private LocalDate createdDate;

    @Column(name = "Game Status", nullable = false)
    private GameStatus gameStatus;

    @Column(name = "Finished at")
    private LocalDate finishedDate;

    @Column(name = "Winner")
    private String winnerName;

    @Transient private int playerScore;
    @Transient private int dealerScore;
    @Transient private List<CardDTO> playerHand;
    @Transient private List<CardDTO> dealerHand;
}