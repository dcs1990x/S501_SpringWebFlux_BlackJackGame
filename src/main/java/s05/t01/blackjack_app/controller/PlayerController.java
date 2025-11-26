package s05.t01.blackjack_app.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import s05.t01.blackjack_app.domain.dtos.PlayerResponseDTO;
import s05.t01.blackjack_app.domain.dtos.UpdatePlayerNameRequestDTO;
import s05.t01.blackjack_app.service.GameService;
import s05.t01.blackjack_app.service.PlayerService;

@RestController
@RequestMapping("/blackjack")
@Tag(name = "Blackjack API", description =" REST API for Blackjack game with R2DBC")
public class PlayerController {

    private final PlayerService playerService;
    private final GameService gameService;

    public PlayerController(PlayerService playerService, GameService gameService) {
        this.playerService = playerService;
        this.gameService = gameService;
    }

    /*@PutMapping("/player/{playerId}")
    @Operation(summary = "Change player name")
    @ApiResponse(responseCode = "200 OK", description = "The player name was changed successfully.")
    public Mono<ResponseEntity<PlayerResponseDTO>> putPlayerName(@PathVariable Long playerId, @RequestBody UpdatePlayerNameRequestDTO updatePlayerNameRequestDTO){
        //return playerService.putPlayerName();
    }*/

    @GetMapping("/ranking")
    @Operation(summary = "Get player ranking")
    @ApiResponse(responseCode = "200 OK", description = "The player ranking was retrieved successfully.")
    public void getPlayersRanking(){
        //return playerService.getPlayersRanking();
    }

    @DeleteMapping("/player/{playerId}/delete")
    @Operation(summary = "Delete a player by ID")
    @ApiResponse(responseCode = "204", description = "The player was deleted successfully.")
    public Mono<ResponseEntity<Void>> deletePlayer(@PathVariable Long playerId) {
        return gameService.deleteGame(playerId)
                .then(Mono.just(ResponseEntity.noContent().build()));
    }
}