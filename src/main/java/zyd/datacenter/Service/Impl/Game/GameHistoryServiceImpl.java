package zyd.datacenter.Service.Impl.Game;

import org.springframework.stereotype.Service;
import zyd.datacenter.Entities.Game.GameHistory;
import zyd.datacenter.Entities.Game.GameOverview;
import zyd.datacenter.Repository.Game.GameHistoryRepository;
import zyd.datacenter.Repository.Game.GameOverviewRepository;
import zyd.datacenter.Service.Game.GameHistoryService;

import java.util.List;

@Service
public class GameHistoryServiceImpl implements GameHistoryService {
    private GameHistoryRepository gameHistoryRepository;

    private GameOverviewRepository gameOverviewRepository;

    public GameHistoryServiceImpl(GameHistoryRepository gameHistoryRepository) {
        this.gameHistoryRepository = gameHistoryRepository;
    }

    public List<GameHistory> getGameHistory(String userId){
        return gameHistoryRepository.findAllByUserId(userId);
    }

    public GameOverview replay(String gameId){
        return gameOverviewRepository.getByGameId(gameId);
    }
}
