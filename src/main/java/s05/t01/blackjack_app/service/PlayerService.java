package s05.t01.blackjack_app.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import s05.t01.blackjack_app.model.entities.PlayerEntity;
import s05.t01.blackjack_app.model.dtos.DTOEntityMapper;
import s05.t01.blackjack_app.repository.SQLGameRepository;
import s05.t01.blackjack_app.repository.SQLPlayerRepository;
import s05.t01.blackjack_app.service.dtos.PlayerResponseDTO;
import java.util.Scanner;

@Service
public class PlayerService {

    private final SQLGameRepository sqlGameRepository;
    private final SQLPlayerRepository sqlPlayerRepository;
    private GameService gameService;
    private DTOEntityMapper dtoEntityMapper;

    public PlayerService(SQLGameRepository sqlGameRepository, SQLPlayerRepository sqlPlayerRepository) {
        this.sqlGameRepository = sqlGameRepository;
        this.sqlPlayerRepository = sqlPlayerRepository;
    }

    public ResponseEntity<PlayerResponseDTO> createPlayer(CreatePlayerRequestDTO createPlayerRequestDTO) {
        PlayerEntity playerEntity = dtoEntityMapper.toEntity(createPlayerRequestDTO);
        String newPlayerName = getPlayerName();
        playerEntity.setPlayerName(newPlayerName);
        PlayerEntity savedEntity = sqlPlayerRepository.save(playerEntity);
        PlayerResponseDTO savedPlayerDTO = dtoEntityMapper.toDTO(savedEntity);
        return ResponseEntity.ok(savedPlayerDTO);
    }

    public String getPlayerName(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type your name: ");
        return scanner.nextLine();
    }
}