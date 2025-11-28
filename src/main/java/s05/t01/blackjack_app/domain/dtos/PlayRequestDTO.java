package s05.t01.blackjack_app.domain.dtos;

import s05.t01.blackjack_app.domain.entities.PlayType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlayRequestDTO {

    private PlayType action;
}