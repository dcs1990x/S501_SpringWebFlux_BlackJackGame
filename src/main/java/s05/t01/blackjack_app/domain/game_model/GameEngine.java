package s05.t01.blackjack_app.domain.game_model;

import s05.t01.blackjack_app.domain.entities.PlayerEntity;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class GameEngine {

    private final DeckFactory deckFactory;
    private final GameState gamestate;
    private final GameStatus gameStatus;

    public GameEngine(DeckFactory deckFactory, GameState gamestate, GameStatus gameStatus) {
        this.deckFactory = deckFactory;
        this.gamestate = gamestate;
        this.gameStatus = gameStatus;
    }

    public GameState initializeGame(PlayerEntity player) {
        List<Card> deck = deckFactory.createShuffledDeck();
        GameState state = new GameState(
                player.getPlayerName(),
                0,
                0,
                deck,
                TurnType.PLAYER_TURN;
        );
        dealInitialCards(state);
        return state;
    }

    private void dealInitialCards(GameState state) {
        List<Card> deck = state.getDeck();
        List<Card> playerCards = new java.util.ArrayList<>();
        List<Card> dealerCards = new java.util.ArrayList<>();

        playerCards.add(draw(deck));
        dealerCards.add(draw(deck));
        playerCards.add(draw(deck));
        dealerCards.add(draw(deck));

        state.setPlayerCards(playerCards);
        state.setDealerCards(dealerCards);

        state.setPlayerScore(calculateScore(playerCards));
        state.setDealerScore(calculateScore(dealerCards));
    }

    private Card draw(List<Card> deck) {
        return deck.removeFirst();
    }

    private GameState drawCardForPlayer(GameState state) {
        Card card = draw(state.getDeck());
        state.getPlayerCards().add(card);
        state.setPlayerScore(calculateScore(state.getPlayerCards()));
        return state;
    }

    private GameState drawCardForDealer(GameState state) {
        Card card = draw(state.getDeck());
        state.getDealerCards().add(card);
        state.setDealerScore(calculateScore(state.getDealerCards()));
        return state;
    }

    public int calculateScore(List<Card> hand) {
        return hand.stream()
                .map(Card::getRank)
                .mapToInt(Rank::getCardValue)
                .sum();
    }

    public GameState applyHit(GameState state) {
        if (state.getResult() != GameResult.IN_PROGRESS) {
            return state;
        }
        if (state.getGamePhase() == TurnType.PLAYER_TURN) {
            drawCardForPlayer(state);

            if (state.getPlayerScore() > 21) {
                state.setResult(GameResult.DEALER_WON);
                gamestate.setGameStatus(GameStatus.FINISHED);
                return state;
            }
            return (state.getPlayerScore() == 21)
                    ? applyStand(state)
                    : state;
        }

        drawCardForDealer(state);
        if (state.getDealerScore() > 21) {
            state.setResult(GameResult.PLAYER_WON);
            gamestate.setGameStatus(GameStatus.FINISHED);
        }
        return state;
    }

    public GameState applyStand(GameState state) {
        if (state.getResult() != GameResult.ONGOING) {
            return state;
        }
        if (state.getGamePhase() == TurnType.PLAYER_TURN) {
            return state;
        }
        state.setGamePhase(TurnType.DEALER_TURN);

        while (state.getDealerScore() < 17 || state.getDealerScore() < state.getPlayerScore()) {
            drawCardForDealer(state);
        }
        decideWinner(state);
        return state;
    }

    private void decideWinner(GameState state) {
        int player = state.getPlayerScore();
        int dealer = state.getDealerScore();

        GameResult result =
                (dealer > 21) ? GameResult.PLAYER_WON :
                        (player > 21) ? GameResult.DEALER_WON :
                                (dealer > player) ? GameResult.DEALER_WON :
                                        (dealer < player) ? GameResult.PLAYER_WON :
                                                GameResult.PUSH;
        state.setResult(result);
    }
}