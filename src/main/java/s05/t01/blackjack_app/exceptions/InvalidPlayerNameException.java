package s05.t01.blackjack_app.exceptions;

public class InvalidPlayerNameException extends RuntimeException {
    public InvalidPlayerNameException() {
        super("Invalid player name.");
    }
}