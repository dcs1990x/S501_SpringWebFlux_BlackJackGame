package s05.t01.blackjack_app.exceptions;

public class InvalidActionException extends RuntimeException {
    public InvalidActionException() {
        super("Invalid action.");
    }
}