package s05.t01.blackjack_app.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import s05.t01.blackjack_app.domain.game_model.Card;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@Data
@AllArgsConstructor
public class CardDTO {

    private String rank;
    private String suit;

    public static CardDTO fromCard(Card card) {
        return CardDTO.builder()
                .rank(String.valueOf(card.getRank()))
                .suit(card.getSuit().name())
                .build();
    }

    public static List<CardDTO> fromCards(List<Card> cards) {
        if (cards == null) return List.of();
        return cards.stream()
                .map(CardDTO::fromCard)
                .collect(Collectors.toList());
    }
}