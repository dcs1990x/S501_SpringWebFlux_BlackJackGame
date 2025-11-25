package s05.t01.blackjack_app.exceptions;

public class GameNotFoundException extends RuntimeException {
    public GameNotFoundException(Long gameId) {
        super("Game with the ID " + gameId + " could not be found");
    }
}