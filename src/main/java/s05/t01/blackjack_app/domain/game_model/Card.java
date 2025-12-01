package s05.t01.blackjack_app.domain.game_model;

import lombok.Value;

@Value
public class Card {

    private final Rank rank;
    private final Suit suit;
}
