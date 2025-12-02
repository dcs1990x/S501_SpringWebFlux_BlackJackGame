package s05.t01.blackjack_app.exceptions;

public class InvalidGameStateException extends RuntimeException {
    public InvalidGameStateException() {
        super("Invalid action.");
    }
}