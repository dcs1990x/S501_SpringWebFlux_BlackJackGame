package s05.t01.blackjack_app.domain.game_model;

import lombok.Getter;

@Getter
public enum Suit {

    HEARTS("Hearts"),
    DIAMONDS("Diamonds"),
    CLUBS("Clubs"),
    SPADES("Spades");

    private final String suit;

    Suit(String suit) {
        this.suit = suit;
    }
}