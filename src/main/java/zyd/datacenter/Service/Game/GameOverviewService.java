package zyd.datacenter.Service.Game;

import zyd.datacenter.Entities.Game.GameOverview;
import zyd.datacenter.Payload.Result;

public interface GameOverviewService {
    public Result addGame(GameOverview gameOverview);
}
