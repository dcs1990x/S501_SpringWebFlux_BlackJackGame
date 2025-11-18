package s05.t01.blackjack_app.entities.mysql;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "games")
public class SQLGameEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Game ID")
    private String gameId;

    @Column(name = "Ongoing/Finished")
    private boolean isFinished;

    public SQLGameEntity(boolean isFinished){
        this.isFinished = false;
    }
}