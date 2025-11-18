package s05.t01.blackjack_app.entities.mysql;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Entity
@Data
@Table(name = "players")
public class SQLPlayerEntity {

    @Column(name = "ID", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long playerId;

    @Column(name = "Name", nullable = false, unique = true)
    @NotBlank
    private String playerName;

    @Column(name = "Number of hands won", nullable = false)
    @NotBlank
    @PositiveOrZero
    private int playerScore;

    @Column(name = "Number of games won", nullable = false)
    @NotBlank
    @PositiveOrZero
    private int playerWins;

    @Column(name = "Number of games lost", nullable = false)
    @NotBlank
    @PositiveOrZero
    private int playerLosses;

    public SQLPlayerEntity(String playerName){
        this.playerName = playerName.trim();
        this.playerScore = 0;
        this.playerWins = 0;
        this.playerLosses = 0;
    }
}