package s05.t01.blackjack_app.domain.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table("players")
public class PlayerEntity {

    @Id
    @Column("id")
    private Long playerId;

    @Column("name")
    @NonNull
    private String playerName;

    @Column("total_games")
    private Integer totalGames;

    @Column("player_wins")
    private Integer playerWins;

    @Column("player_losses")
    private Integer playerLosses;

    @Column("created_date")
    private Instant createdDate;

    public Integer calculateTotalGames() {
        return (playerWins != null ? playerWins : 0) +
                (playerLosses != null ? playerLosses : 0);
    }

    public Double calculateWinRate() {
        int total = calculateTotalGames();
        if (total == 0) {
            return 0.0;
        }
        return (playerWins != null ? (double) playerWins / total * 100 : 0.0);
    }
}