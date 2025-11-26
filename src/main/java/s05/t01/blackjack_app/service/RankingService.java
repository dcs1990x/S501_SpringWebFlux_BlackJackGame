package s05.t01.blackjack_app.service;

import reactor.core.publisher.Mono;
import s05.t01.blackjack_app.domain.dtos.RankingResponseDTO;
import org.springframework.stereotype.Service;
import s05.t01.blackjack_app.domain.entities.PlayerEntity;
import s05.t01.blackjack_app.repository.PlayerRepository;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class RankingService {

    PlayerRepository playerRepository;

    public RankingService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    private RankingResponseDTO toRankingResponseDTO(PlayerEntity player) {
        return RankingResponseDTO.builder()
                .playerName(player.getPlayerName())
                .gamesWon(player.getPlayerWins())
                .gamesLost(player.getPlayerLosses())
                .winRate(player.calculateWinRate() != null ?
                        (int) Math.round(player.calculateWinRate()) : 0)
                .build();
    }

    public Mono<List<RankingResponseDTO>> getPlayerRanking() {
        return playerRepository.findAll()
                .collectList()
                .flatMap(players -> {
                    List<PlayerEntity> sortedPlayers = players.stream()
                            .sorted(Comparator.comparing(PlayerEntity::calculateWinRate).reversed()
                                    .thenComparing(Comparator.comparing(PlayerEntity::getPlayerWins).reversed()))
                            .toList();

                    List<RankingResponseDTO> ranking = new ArrayList<>();
                    for (int i = 0; i < sortedPlayers.size(); i++) {
                        RankingResponseDTO dto = toRankingResponseDTO(sortedPlayers.get(i));
                        dto.setPosition(i + 1);
                        ranking.add(dto);
                    }

                    return Mono.just(ranking);
                });
    }
}