package s05.t01.blackjack_app.domain.game_model;

import lombok.Getter;

@Getter
public enum GameResult {

    ONGOING("-"),
    PLAYER_WON("Player won"),
    DEALER_WON("Dealer won"),
    DRAW("Draw");

    private final String gameResultMessage;

    GameResult(String gameResultMessage){
        this.gameResultMessage = gameResultMessage;
    }
}