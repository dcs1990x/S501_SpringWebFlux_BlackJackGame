package s05.t01.blackjack_app.service.dtos;

public record SQLPlayerRequestDTO (String playerName, int playerScore, int playerWins, int playerLosses){}