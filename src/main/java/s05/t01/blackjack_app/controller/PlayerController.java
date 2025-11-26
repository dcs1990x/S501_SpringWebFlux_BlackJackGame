package s05.t01.blackjack_app.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import s05.t01.blackjack_app.domain.dtos.PlayerResponseDTO;
import s05.t01.blackjack_app.domain.dtos.RankingResponseDTO;
import s05.t01.blackjack_app.domain.dtos.UpdatePlayerNameRequestDTO;
import s05.t01.blackjack_app.service.*;

import java.util.List;

@RestController
@RequestMapping("/blackjack")
@Tag(name = "Blackjack API", description =" REST API for Blackjack game with R2DBC")
public class PlayerController {

    private final PlayerService playerService;
    private final GameService gameService;
    private final RankingService rankingService;

    public PlayerController(PlayerService playerService, GameService gameService, RankingService rankingService) {
        this.playerService = playerService;
        this.gameService = gameService;
        this.rankingService = rankingService;
    }

    @PutMapping("/player/{playerId}")
    @Operation(summary = "Change player name")
    @ApiResponse(responseCode = "200 OK", description = "The player name was changed successfully.")
    public Mono<ResponseEntity<PlayerResponseDTO>> putPlayerName(@PathVariable Long playerId, @RequestBody UpdatePlayerNameRequestDTO updatePlayerNameRequestDTO){
        return playerService.updatePlayerName(playerId, updatePlayerNameRequestDTO)
                .map(playerResponseDTO -> ResponseEntity.status(HttpStatus.OK).body(playerResponseDTO));
    }

    @GetMapping("/ranking")
    @Operation(summary = "Get player ranking")
    @ApiResponse(responseCode = "200 OK", description = "The player ranking was retrieved successfully.")
    public Mono<ResponseEntity<List<RankingResponseDTO>>> getPlayersRanking(){
        return rankingService.getPlayerRanking()
                .map(rankingResponseDTO -> ResponseEntity.status(HttpStatus.OK).body(rankingResponseDTO));
    }

    @DeleteMapping("/player/{playerId}/delete")
    @Operation(summary = "Delete a player by ID")
    @ApiResponse(responseCode = "204", description = "The player was deleted successfully.")
    public Mono<ResponseEntity<Void>> deletePlayer(@PathVariable Long playerId) {
        return gameService.deleteGame(playerId)
                .then(Mono.just(ResponseEntity.noContent().build()));
    }
}