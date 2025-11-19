package s05.t01.blackjack_app.service.dtos;

public record SQLGameRequestDTO(String playerName, boolean isFinished, String winnerName){}