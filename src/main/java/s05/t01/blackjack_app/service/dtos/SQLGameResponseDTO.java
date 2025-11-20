package s05.t01.blackjack_app.service.dtos;

import java.time.LocalDate;

public record SQLGameResponseDTO(Long gameId, String playerName, LocalDate createdDate, boolean isFinished, String winnerName){}