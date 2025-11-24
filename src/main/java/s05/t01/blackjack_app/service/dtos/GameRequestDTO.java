package s05.t01.blackjack_app.service.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameRequestDTO{

    @NotBlank(message = "Please choose a player name: ")
    private String playerName;

    public GameRequestDTO(String playerName){
        this.playerName = playerName;
    }
}