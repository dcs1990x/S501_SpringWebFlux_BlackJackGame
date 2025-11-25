package s05.t01.blackjack_app.service;

import org.springframework.stereotype.Service;
import s05.t01.blackjack_app.model.entities.PlayerEntity;
import s05.t01.blackjack_app.model.dtos.DTOEntityMapper;
import s05.t01.blackjack_app.repository.SQLGameRepository;
import s05.t01.blackjack_app.repository.SQLPlayerRepository;
import s05.t01.blackjack_app.model.dtos.PlayerResponseDTO;
import java.util.List;
import java.util.Scanner;

@Service
public class PlayerService {

    private final SQLGameRepository sqlGameRepository;
    private final SQLPlayerRepository sqlPlayerRepository;
    private DTOEntityMapper dtoEntityMapper;

    public PlayerService(SQLGameRepository sqlGameRepository, SQLPlayerRepository sqlPlayerRepository) {
        this.sqlGameRepository = sqlGameRepository;
        this.sqlPlayerRepository = sqlPlayerRepository;
    }

    public PlayerResponseDTO createPlayer(CreatePlayerRequestDTO createPlayerRequestDTO) {
        PlayerEntity playerEntity = dtoEntityMapper.toEntity(createPlayerRequestDTO);
        String newPlayerName = getPlayerName();
        playerEntity.setPlayerName(newPlayerName);
        PlayerEntity savedEntity = sqlPlayerRepository.save(playerEntity);
        PlayerResponseDTO savedPlayerDTO = dtoEntityMapper.toDTO(savedEntity);
        return savedPlayerDTO;
    }

    public List<PlayerEntity> getAllPlayers() {
        return SQLPlayerRepository.findAll();
    }

    public PlayerResponseDTO getPlayerById(Long playerId) {
        PlayerEntity player = SQLPlayerRepository.findById(playerId)
                .orElseThrow(() -> new PlayerNotFoundException("Player with ID " + playerId + " was not found."));
        return PlayerResponseDTO.fromEntity(player);
    }

    public void deletePlayer(Long playerId) {
        if (!SQLPlayerRepository.existsById(playerId)) {
            throw new PlayerNotFoundException("Player with ID " + playerId + " was not found.");
        }
        SQLPlayerRepository.deleteById(playerId);
    }
}