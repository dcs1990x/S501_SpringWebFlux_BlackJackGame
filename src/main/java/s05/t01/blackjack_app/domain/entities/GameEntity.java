package s05.t01.blackjack_app.domain.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import s05.t01.blackjack_app.domain.game_model.*;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "games")
public class GameEntity {

    @Id
    private String id;

    @Indexed
    @Field("game_id")
    private Long gameId;

    @Indexed
    @NonNull
    @Field("player_id")
    private Long playerId;

    @NonNull
    @Field("player_name")
    private String playerName;

    @NonNull
    @Field("created_date")
    private Instant createdDate;

    @NonNull
    @Field("game_status")
    private GameStatus gameStatus;

    @Field("finished_date")
    private Instant finishedDate;

    @Field("game_result")
    private GameResult gameResult;

    @Field("game_turn_phase")
    private GamePhase gamePhase;
}