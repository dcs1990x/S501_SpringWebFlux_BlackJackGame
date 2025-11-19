package s05.t01.blackjack_app.service.dtos;

public record SQLGameResponseDTO(Long gameId, String playerName, boolean isFinished, String winnerName){}