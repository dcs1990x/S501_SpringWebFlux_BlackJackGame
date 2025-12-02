package s05.t01.blackjack_app.domain.game_model;

import lombok.Getter;

@Getter
public enum GamePhase {

    PLAYER_TURN, DEALER_TURN, GAME_OVER;

    public boolean isPlayerTurn() {
        return this == PLAYER_TURN;
    }
}
