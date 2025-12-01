package s05.t01.blackjack_app.domain.game_model;

public enum GameResult {

    ONGOING("-"),
    PLAYER_WON("Player won"),
    DEALER_WON("Dealer won"),
    PUSH("Push");

    private String gameResultMessage;

    GameResult(String gameResultMessage){
        this.gameResultMessage = gameResultMessage;
    }

    public String getGameResultMessage(){
        return this.gameResultMessage;
    }
}