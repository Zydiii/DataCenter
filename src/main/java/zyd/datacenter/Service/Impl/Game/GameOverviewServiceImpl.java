package zyd.datacenter.Service.Impl.Game;

import org.springframework.stereotype.Service;
import zyd.datacenter.Entities.Game.GameOverview;
import zyd.datacenter.Payload.Result;
import zyd.datacenter.Repository.Game.GameOverviewRepository;
import zyd.datacenter.Service.Game.GameOverviewService;

import java.util.List;

@Service
public class GameOverviewServiceImpl implements GameOverviewService {
    private GameOverviewRepository gameOverviewRepository;

    public GameOverviewServiceImpl(GameOverviewRepository gameOverviewRepository) {
        this.gameOverviewRepository = gameOverviewRepository;
    }

    public Result addGame(GameOverview gameOverview){
        gameOverviewRepository.insert(gameOverview);
        return new Result("创建成功", 1);
    }

    public List<GameOverview> getAllGameOverview(){
        return gameOverviewRepository.findAll();
    }
}
