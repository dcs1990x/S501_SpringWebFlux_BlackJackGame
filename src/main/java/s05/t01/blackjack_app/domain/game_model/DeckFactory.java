package s05.t01.blackjack_app.domain.game_model;

import java.util.List;

public interface DeckFactory {

    List<Card> createSuffledDeck();
}