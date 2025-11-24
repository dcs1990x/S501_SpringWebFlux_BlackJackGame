package s05.t01.blackjack_app.model.entities;

public enum GameStatus {

    NOT_STARTED("Not Started"),
    ONGOING("Ongoing"),
    FINISHED("Finished");

    private final String statusMessage;

    GameStatus(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public String getStatusMessage(){
        return this.statusMessage;
    }
}