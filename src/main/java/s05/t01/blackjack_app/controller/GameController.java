package s05.t01.blackjack_app.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/blackjack")
@Tag(name="Blackjack API", description="REST API for Blackjack game with R2DBC")
public class GameController {

    //private final GameService service;

    @PostMapping("/game/new")
    @Operation(summary = "Create a new game")
    @ApiResponse(responseCode = "201 CREATED", description = "The game was created successfully.")
    public void postNewGame(@RequestParam(value = "name", defaultValue = "user") String playerName){
        //return service.createNewGame();
    }

    @PostMapping("/game/{id}/play")
    @Operation(summary = "Play a hand")
    @ApiResponse(responseCode = "200 OK", description = "The hand was played. ")
    public void postHand(@PathVariable Long gameId,
                         @RequestBody(Hand hand)){
        //return service.playHand();
    }

    @GetMapping("/game/{userId}")
    @Operation(summary = "Get game details by game ID")
    @ApiResponse(responseCode = "200 OK", description = "The game with the entered ID was found.")
    @ApiResponse(responseCode = "404 NOT FOUND", description = "A game with the entered ID could not be found.")
    public void getGameDetailsById(@PathVariable Long gameId){
        //return service.findById();
    }

    @DeleteMapping("/game/{id}/delete")
    @Operation(summary = "Delete a game by ID")
    @ApiResponse(responseCode = "204 NO CONTENT", description = "The game was deleted successfully.")
    public void deleteGameById(@PathVariable Long gameId){
        //return service.deleteGameById();
    }

    @GetMapping("/ranking")
    @Operation(summary = "Get player ranking")
    @ApiResponse(responseCode = "200 OK", description = "The player ranking was retrieved successfully.")
    public void getPlayersRanking(){
        //return service.getPlayersRanking();
    }

    @PutMapping("/player/{playerId}")
    @Operation(summary = "Change player name")
    @ApiResponse(responseCode = "200 OK", description = "The player name was changed successfully.")
    public void putPlayerName(@PathVariable Long playerId){
        //return service.putPlayerName();
    }
}