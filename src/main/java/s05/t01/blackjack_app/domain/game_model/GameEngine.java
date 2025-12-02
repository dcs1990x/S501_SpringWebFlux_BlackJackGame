package s05.t01.blackjack_app.domain.game_model;

import s05.t01.blackjack_app.domain.entities.PlayerEntity;
import org.springframework.stereotype.Component;
import java.time.Instant;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

@Component
public class GameEngine {

    private final DeckFactory deckFactory;

    public GameEngine(DeckFactory deckFactory) {
        this.deckFactory = deckFactory;
    }

    public GameState initializeGame(PlayerEntity player) {
        Deque<Card> deck = new ArrayDeque<>(deckFactory.createShuffledDeck());
        GameState gameState = new GameState(
                player.getPlayerName(),
                Instant.now(),
                0,
                0,
                new ArrayList<>(deck),
                GamePhase.PLAYER_TURN,
                GameStatus.IN_PROGRESS,
                GameResult.ONGOING
        );
        dealInitialCards(gameState, deck);
        return gameState;
    }

    private void dealInitialCards(GameState state, Deque<Card> deck) {
        List<Card> playerCards = new ArrayList<>();
        List<Card> dealerCards = new ArrayList<>();

        playerCards.add(draw(deck));
        dealerCards.add(draw(deck));
        playerCards.add(draw(deck));
        dealerCards.add(draw(deck));

        state.setPlayerCards(playerCards);
        state.setDealerCards(dealerCards);
        state.setDeck(new ArrayList<>(deck));

        updateScores(state);
    }

    private Card draw(Deque<Card> deck) {
        return deck.pollFirst(); // más seguro que removeFirst
    }

    private void updateScores(GameState state) {
        state.setPlayerScore(calculateScore(state.getPlayerCards()));
        state.setDealerScore(calculateScore(state.getDealerCards()));
    }

    public int calculateScore(List<Card> hand) {
        int total = 0;
        int aceCount = 0;

        for (Card card : hand) {
            int value = card.getRank().getCardValue();
            if (card.getRank() == Rank.ACE) {
                aceCount++;
                value = 11;
            }
            total += value;
        }

        while (total > 21 && aceCount > 0) {
            total -= 10;
            aceCount--;
        }
        return total;
    }

    public void chooseHit(GameState state) {
        if (state.getGameStatus() != GameStatus.IN_PROGRESS || state.getGamePhase() != GamePhase.PLAYER_TURN) {
            return;
        }
        state.getPlayerCards().add(drawDeck(state));
        updateScores(state);

        if (state.getPlayerScore() >= 21) {
            chooseStand(state);
        }
    }

    public void chooseStand(GameState state) {
        if (state.getGameStatus() != GameStatus.IN_PROGRESS) return;

        state.setGamePhase(GamePhase.DEALER_TURN);
        while (state.getDealerScore() < 17 || state.getDealerScore() < state.getPlayerScore()) {
            state.getDealerCards().add(drawDeck(state));
            updateScores(state);
        }
        decideWinner(state);
    }

    private Card drawDeck(GameState state) {
        Deque<Card> deck = new ArrayDeque<>(state.getDeck());
        Card card = draw(deck);
        state.setDeck(new ArrayList<>(deck));
        return card;
    }

    public void decideWinner(GameState gameState) {
        int playerScore = calculateScore(gameState.getPlayerCards());
        int dealerScore = calculateScore(gameState.getDealerCards());

        gameState.setPlayerScore(playerScore);
        gameState.setDealerScore(dealerScore);

        if (playerScore > 21) {
            gameState.setGameResult(GameResult.DEALER_WON);
            gameState.setGameStatus(GameStatus.FINISHED);
            gameState.setGamePhase(GamePhase.GAME_OVER);
            return;
        }
        if (dealerScore > 21) {
            gameState.setGameResult(GameResult.PLAYER_WON);
            gameState.setGameStatus(GameStatus.FINISHED);
            gameState.setGamePhase(GamePhase.GAME_OVER);
            return;
        }
        if (gameState.getGamePhase() == GamePhase.PLAYER_TURN) {
            gameState.setGameStatus(GameStatus.IN_PROGRESS);
            return;
        }
        if (gameState.getGamePhase() == GamePhase.DEALER_TURN && dealerScore >= 17) {
            if (playerScore > dealerScore) gameState.setGameResult(GameResult.PLAYER_WON);
            else if (dealerScore > playerScore) gameState.setGameResult(GameResult.DEALER_WON);
            else gameState.setGameResult(GameResult.DRAW);

            gameState.setGameStatus(GameStatus.FINISHED);
            gameState.setGamePhase(GamePhase.GAME_OVER);
        }
    }
}