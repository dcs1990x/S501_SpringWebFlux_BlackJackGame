package s05.t01.blackjack_app.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import s05.t01.blackjack_app.entities.mysql.SQLPlayerEntity;
import s05.t01.blackjack_app.service.GameService;
import s05.t01.blackjack_app.service.PlayerService;
import s05.t01.blackjack_app.service.dtos.SQLGameResponseDTO;

@RestController
@RequestMapping("/blackjack")
@Tag(name = "Blackjack API", description = "REST API for Blackjack game with R2DBC")
public class GameController {

    private final GameService gameService;
    private final PlayerService playerService;

    public GameController(GameService gameService, PlayerService playerService) {
        this.gameService = gameService;
        this.playerService = playerService;
    }

    @PostMapping("/game/new")
    @Operation(summary = "Create a new game")
    @ApiResponse(responseCode = "201 CREATED", description = "The game was created successfully.")
    public ResponseEntity<SQLGameResponseDTO> postNewGame(@RequestBody SQLPlayerEntity sqlPlayerEntity) {
        gameService.createNewGame(sqlPlayerEntity.getPlayerName());
        return ResponseEntity.status(HttpStatus.CREATED).body(new SQLGameResponseDTO());
    }

    @PostMapping("/game/{id}/play")
    @Operation(summary = "Play a hand")
    @ApiResponse(responseCode = "200 OK", description = "The hand was played. ")
    public void postHand(@PathVariable Long gameId,
                         @RequestBody Hand hand){
        //service.playHand();
        //return ResponseEntity.ok().body();
    }

    @GetMapping("/game/{gameId}")
    @Operation(summary = "Get game details by game ID")
    @ApiResponse(responseCode = "200 OK", description = "The game with the entered ID was found.")
    @ApiResponse(responseCode = "404 NOT FOUND", description = "A game with the entered ID could not be found.")
    public void getGameDetailsById(@PathVariable Long gameId){
        //return ResponseEntity.ok().body();
    }

    @DeleteMapping("/game/{id}/delete")
    @Operation(summary = "Delete a game by ID")
    @ApiResponse(responseCode = "204 NO CONTENT", description = "The game was deleted successfully.")
    public void deleteGameById(@PathVariable Long gameId){
        //service.deleteGameById();
        //return ResponseEntity.status(HttpStatus.NO_CONTENT).body();
    }
}