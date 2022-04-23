package site.metacoding.greenrandomrpg.service.game;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import site.metacoding.greenrandomrpg.domain.game.Game;
import site.metacoding.greenrandomrpg.domain.game.GameRepository;

@RequiredArgsConstructor
@Service
public class GameService {

    private final GameRepository gameRepository;

    public List<Game> 게임목록가져오기() {
        List<Game> games = gameRepository.findAll();
        return games;
    }
}
