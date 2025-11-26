package s05.t01.blackjack_app.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "players")
public class PlayerEntity {

    @Column(name = "ID", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long playerId;

    @Column(name = "Name", nullable = false, unique = true)
    @NonNull
    @NotBlank
    private String playerName;

    @Column(name = "Number of games won", nullable = false)
    @NotBlank
    @PositiveOrZero
    private int playerWins;

    @Column(name = "Number of games lost", nullable = false)
    @NotBlank
    @PositiveOrZero
    private int playerLosses;

    public int calculateTotalGames(){
        return this.playerWins + this.playerLosses;
    }

    public Double calculateWinRate() {
        int totalGames = calculateTotalGames();
        if (totalGames == 0) {
            return 0.0;
        }
        return (double) ((this.playerWins / totalGames) * 100);
    }
}