package s05.t01.blackjack_app.domain.game_model;

public enum GameStatus {

    IN_PROGRESS("In progress"),
    FINISHED("Finished");

    private String statusMessage;

    GameStatus(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public String getStatusMessage(){
        return this.statusMessage;
    }
}