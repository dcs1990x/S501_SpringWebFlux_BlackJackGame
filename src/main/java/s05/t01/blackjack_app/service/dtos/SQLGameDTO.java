package s05.t01.blackjack_app.service.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import s05.t01.blackjack_app.entities.mysql.SQLGameEntity;

@Getter
@Setter
@ToString
public class SQLGameDTO {

    private Long gameId;
    private String playerName;
    private boolean isFinished;
    private String winnerName;

    public SQLGameDTO(SQLGameEntity sqlGameEntity){
        this.gameId = sqlGameEntity.getGameId();
        this.playerName = sqlGameEntity.getPlayerName();
        this.isFinished = sqlGameEntity.isFinished();
        this.winnerName = sqlGameEntity.getWinnerName();
    }
}