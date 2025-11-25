package s05.t01.blackjack_app.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import reactor.core.publisher.Mono;
import s05.t01.blackjack_app.model.dtos.DTOEntityMapper;
import s05.t01.blackjack_app.model.entities.GameEntity;
import s05.t01.blackjack_app.service.GameService;
import s05.t01.blackjack_app.model.dtos.CreateGameRequestDTO;
import s05.t01.blackjack_app.model.dtos.GameResponseDTO;

@RestController
@RequestMapping("/game")
@Tag(name = "Blackjack API", description = "REST API for Blackjack game with R2DBC")
public class GameController {

    private final GameService gameService;
    private final DTOEntityMapper dtoEntityMapper;

    public GameController(GameService gameService, DTOEntityMapper dtoEntityMapper) {
        this.gameService = gameService;
        this.dtoEntityMapper = dtoEntityMapper;
    }

    @PostMapping("/game/new")
    @Operation(summary = "Create a new game")
    @ApiResponse(responseCode = "201", description = "The game was created successfully.")
    public Mono<ResponseEntity<GameResponseDTO>> postNewGame(@RequestBody CreateGameRequestDTO gameRequestDTO) {
        Mono<GameEntity> newGame = gameService.createGame(gameRequestDTO.getPlayerName());
        GameResponseDTO newGameDTO = dtoEntityMapper.toDTO(newGame);
        return Mono.just(ResponseEntity.status(HttpStatus.CREATED).body(newGameDTO));
    }

    @PutMapping("/game/{id}/play")
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
    public Mono<ResponseEntity<GameResponseDTO>> getGameDetailsById(@PathVariable Long gameId){
        Mono<GameEntity> gotGamebyId = gameService.getGameById(gameId);
        GameResponseDTO newGameDTO = dtoEntityMapper.toDTO(gotGamebyId);
        return Mono.just(ResponseEntity.status(HttpStatus.OK).body(newGameDTO));
    }

    @DeleteMapping("/game/{id}/delete")
    @Operation(summary = "Delete a game by ID")
    @ApiResponse(responseCode = "204", description = "The game was deleted successfully.")
    public Mono<ResponseEntity<Void>> deleteGame(@PathVariable Long id) {
        return gameService.deleteGame(id)
                .then(Mono.just(ResponseEntity.noContent().build()));
    }
}