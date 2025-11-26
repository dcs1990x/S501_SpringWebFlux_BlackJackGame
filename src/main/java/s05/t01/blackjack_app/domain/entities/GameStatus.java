package s05.t01.blackjack_app.domain.entities;

public enum GameStatus {

    IN_PROGRESS("In Progress"),
    FINISHED("Finished");

    private final String statusMessage;

    GameStatus(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public String getStatusMessage(){
        return this.statusMessage;
    }
}