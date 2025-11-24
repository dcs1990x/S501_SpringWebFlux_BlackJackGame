package s05.t01.blackjack_app.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import s05.t01.blackjack_app.entities.mysql.SQLGameEntity;
import s05.t01.blackjack_app.repository.SQLGameRepository;
import s05.t01.blackjack_app.repository.SQLPlayerRepository;
import s05.t01.blackjack_app.service.dtos.GameResponseDTO;
import s05.t01.blackjack_app.service.dtos.PlayerRequestDTO;

@Service
public class GameService {

    private final SQLGameRepository sqlGameRepository;
    private final SQLPlayerRepository sqlPlayerRepository;
    private final PlayerService playerService;

    public GameService(SQLGameRepository sqlGameRepository, SQLPlayerRepository sqlPlayerRepository, PlayerService playerService) {
        this.sqlGameRepository = sqlGameRepository;
        this.sqlPlayerRepository = sqlPlayerRepository;
        this.playerService = playerService;
    }

    public ResponseEntity<GameResponseDTO> createNewGame(PlayerRequestDTO playerRequestDTO){
        String enteredPlayerName = playerService.createPlayer(playerRequestDTO);
        SQLGameEntity savedGame = sqlGameRepository.save(sqlGameEntity);
        return
    }
}