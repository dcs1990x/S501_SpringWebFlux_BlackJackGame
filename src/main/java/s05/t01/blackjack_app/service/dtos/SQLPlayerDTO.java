package s05.t01.blackjack_app.service.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import s05.t01.blackjack_app.entities.mysql.SQLPlayerEntity;

@Getter
@Setter
@ToString
public class SQLPlayerDTO {

    private Long playerId;
    private String playerName;
    private int playerScore;
    private int playerWins;
    private int playerLosses;

    public SQLPlayerDTO(){}

    public SQLPlayerDTO(SQLPlayerEntity sqlPlayerEntity){
        this.playerName = sqlPlayerEntity.getPlayerName();
        this.playerScore = sqlPlayerEntity.getPlayerScore();
        this.playerWins = sqlPlayerEntity.getPlayerWins();
        this.playerLosses = sqlPlayerEntity.getPlayerLosses();
    }
}