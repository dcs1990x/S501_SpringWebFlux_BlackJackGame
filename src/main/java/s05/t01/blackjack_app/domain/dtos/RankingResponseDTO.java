package s05.t01.blackjack_app.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RankingResponseDTO {
    private Integer position;
    private String playerName;
    private Integer gamesWon;
    private Integer gamesLost;
    private Integer winRate;
}