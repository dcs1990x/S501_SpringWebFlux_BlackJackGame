package s05.t01.blackjack_app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import s05.t01.blackjack_app.domain.dtos.RankingResponseDTO;
import s05.t01.blackjack_app.service.RankingService;
import java.util.List;

@RestController
@RequestMapping("/ranking")
@RequiredArgsConstructor
public class RankingController {

    private final RankingService rankingService;

    @GetMapping
    public Mono<ResponseEntity<List<RankingResponseDTO>>> getPlayersRanking() {
        return rankingService.getPlayerRanking()
                .map(ResponseEntity::ok);
    }
}