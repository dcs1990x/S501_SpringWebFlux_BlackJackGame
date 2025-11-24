package s05.t01.blackjack_app.model.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdatePlayerNameRequestDTO {

    @NotBlank(message = "Please choose a new player name.")
    @Size(min = 3, max = 10, message = "Player name must be between 3 and 10 characters long.")
    private String newPlayerName;
}
