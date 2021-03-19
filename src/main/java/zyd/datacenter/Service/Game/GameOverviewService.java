package zyd.datacenter.Service.Game;

import zyd.datacenter.Entities.Game.GameOverview;
import zyd.datacenter.Payload.Result;

import java.util.List;

public interface GameOverviewService {
    public Result addGame(GameOverview gameOverview);

    public List<GameOverview> getAllGameOverview();
}
