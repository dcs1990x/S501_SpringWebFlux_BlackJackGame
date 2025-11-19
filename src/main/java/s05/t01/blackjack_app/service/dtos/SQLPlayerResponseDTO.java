package s05.t01.blackjack_app.service.dtos;

public record SQLPlayerResponseDTO(Long playerId, String playerName, int playerScore, int playerWins, int playerLosses){}