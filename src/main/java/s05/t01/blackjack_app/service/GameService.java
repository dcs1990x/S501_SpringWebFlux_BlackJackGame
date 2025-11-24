package s05.t01.blackjack_app.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import s05.t01.blackjack_app.model.dtos.CreateGameRequestDTO;
import s05.t01.blackjack_app.model.entities.GameEntity;
import s05.t01.blackjack_app.repository.SQLGameRepository;
import s05.t01.blackjack_app.repository.SQLPlayerRepository;
import s05.t01.blackjack_app.model.dtos.GameResponseDTO;

@Service
public class GameService {

    private final SQLGameRepository sqlGameRepository;
    private final SQLPlayerRepository sqlPlayerRepository;

    public GameService(SQLGameRepository sqlGameRepository, SQLPlayerRepository sqlPlayerRepository, PlayerService playerService) {
        this.sqlGameRepository = sqlGameRepository;
        this.sqlPlayerRepository = sqlPlayerRepository;
    }

    public ResponseEntity<GameResponseDTO> createNewGame(CreateGameRequestDTO createGameRequestDTO){
        String enteredPlayerName = playerService.createPlayer(createPlayerRequestDTO);
        GameEntity savedGame = sqlGameRepository.save(sqlGameEntity);
        return
    }
}