package s05.t01.blackjack_app.domain.game_model;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class Deck implements DeckFactory{

    private SecureRandom randomNum;

    public List<Card> createSuffledDeck() {
        List<Card> deck =
                Arrays.stream(Suit.values())
                        .flatMap(suit ->
                                Arrays.stream(Rank.values())
                                        .map(rank -> new Card(rank, suit))
                        )
                        .collect(Collectors.toList());
        Collections.shuffle(deck, randomNum);
        return deck;
    }
}