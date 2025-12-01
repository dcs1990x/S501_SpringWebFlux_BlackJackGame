package s05.t01.blackjack_app.domain.game_model;

import lombok.Getter;

@Getter
public enum GameTurnPhase {

    SETUP("Setting up new game"),
    INITIAL_DEAL("The cards were handed."),
    PLAYER_TURN("The player made their play."),
    PLAYER_RESOLVED("It's the dealer's turn."),
    DEALER_TURN("It's the dealer's turn."),
    DEALER_RESOLVED("The dealer made their play."),
    RESOLUTION("Comparing scores."),
    GAME_OVER("The game is over.");

    private String turnMessage;

    GameTurnPhase(String turnMessage){
        this.turnMessage = turnMessage;
    }

    public boolean isGameActive() {
        return this != GAME_OVER && this != RESOLUTION;
    }

    public boolean isPlayerTurn() {
        return this == PLAYER_TURN;
    }
}
