package s05.t01.blackjack_app.exceptions;

public class PlayerNotFoundException extends RuntimeException {
    public PlayerNotFoundException(Long playerId) {
        super("Player with ID " + playerId + " could not be found.");
    }
}