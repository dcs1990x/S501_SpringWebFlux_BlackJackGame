package s05.t01.blackjack_app.service;

import org.springframework.stereotype.Service;
import s05.t01.blackjack_app.entities.mysql.SQLPlayerEntity;
import s05.t01.blackjack_app.repository.SQLGameRepository;
import s05.t01.blackjack_app.repository.SQLPlayerRepository;
import java.util.Scanner;

@Service
public class PlayerService {

    private final SQLGameRepository sqlGameRepository;
    private final SQLPlayerRepository sqlPlayerRepository;
    private final GameService gameService;

    public PlayerService(SQLGameRepository sqlGameRepository, SQLPlayerRepository sqlPlayerRepository, GameService gameService) {
        this.sqlGameRepository = sqlGameRepository;
        this.sqlPlayerRepository = sqlPlayerRepository;
        this.gameService = gameService;
    }

    public ResponseEntity<PlayerDTO> createPlayer(){
        String newPlayerName = getPlayerName();
        SQLPlayerEntity newPlayer = new SQLPlayerEntity(newPlayerName);
        return new ResponseEntity<PlayerDTO>(newPlayer);
    }

    public String getPlayerName(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type your name: ");
        return scanner.nextLine();
    }

}