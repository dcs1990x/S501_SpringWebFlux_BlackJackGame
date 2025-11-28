package s05.t01.blackjack_app.exceptions;

public class InvalidPlayException extends RuntimeException {
    public InvalidPlayException() {
        super("Invalid action.");
    }
}