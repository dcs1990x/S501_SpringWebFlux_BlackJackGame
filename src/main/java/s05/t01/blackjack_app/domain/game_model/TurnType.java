package s05.t01.blackjack_app.domain.game_model;

import lombok.Getter;

@Getter
public enum TurnType {

    PLAYER_TURN, DEALER_TURN, GAME_OVER;

    public boolean isGameActive() {
        if (this.equals(PLAYER_TURN) || this.equals(DEALER_TURN)){
            return true;
        }
        return false;
    }

    public boolean isPlayerTurn() {
        return this == PLAYER_TURN;
    }
}
