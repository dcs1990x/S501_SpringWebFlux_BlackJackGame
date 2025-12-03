package s05.t01.blackjack_app.domain.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateGameRequestDTO {

    @NotBlank(message = "Please choose a player name.")
    @Size(min = 3, max = 10, message = "Player name must be between 3 and 10 characters long.")
    private String playerName;
}