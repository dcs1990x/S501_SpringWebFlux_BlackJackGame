package s05.t01.blackjack_app.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CardDTO {

    private String rank;
    private String suit;
}