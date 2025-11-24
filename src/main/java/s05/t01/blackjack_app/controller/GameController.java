package s05.t01.blackjack_app.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import s05.t01.blackjack_app.service.GameService;
import s05.t01.blackjack_app.service.dtos.GameRequestDTO;
import s05.t01.blackjack_app.service.dtos.GameResponseDTO;

@RestController
@RequestMapping("/game")
@Tag(name = "Blackjack API", description = "REST API for Blackjack game with R2DBC")
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/game/new")
    @Operation(summary = "Create a new game")
    @ApiResponse(responseCode = "201", description = "The game was created successfully.")
    public ResponseEntity<GameResponseDTO> postNewGame(@RequestBody GameRequestDTO gameRequestDTO) {
        gameService.createNewGame(gameRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body();
    }

    @PostMapping("/game/{id}/play")
    @Operation(summary = "Play a hand")
    @ApiResponse(responseCode = "200", description = "The hand was played. ")
    public void postHand(@PathVariable Long gameId,
                         @RequestBody Hand hand){
        //service.playHand();
        //return ResponseEntity.ok().body();
    }

    @GetMapping("/game/{gameId}")
    @Operation(summary = "Get game details by game ID")
    @ApiResponse(responseCode = "200", description = "The game with the entered ID was found.")
    @ApiResponse(responseCode = "404", description = "A game with the entered ID could not be found.")
    public void getGameDetailsById(@PathVariable Long gameId){
        //return ResponseEntity.ok().body();
    }

    @DeleteMapping("/game/{id}/delete")
    @Operation(summary = "Delete a game by ID")
    @ApiResponse(responseCode = "204", description = "The game was deleted successfully.")
    public void deleteGameById(@PathVariable Long gameId){
        //service.deleteGameById();
        //return ResponseEntity.status(HttpStatus.NO_CONTENT).body();
    }
}