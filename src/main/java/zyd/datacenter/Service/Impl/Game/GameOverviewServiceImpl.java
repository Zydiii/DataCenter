package zyd.datacenter.Service.Impl.Game;

import org.springframework.stereotype.Service;
import zyd.datacenter.Repository.Game.GameOverviewRepository;
import zyd.datacenter.Service.Game.GameOverviewService;

@Service
public class GameOverviewServiceImpl implements GameOverviewService {
    private GameOverviewRepository gameOverviewRepository;

    public GameOverviewServiceImpl(GameOverviewRepository gameOverviewRepository) {
        this.gameOverviewRepository = gameOverviewRepository;
    }
}
