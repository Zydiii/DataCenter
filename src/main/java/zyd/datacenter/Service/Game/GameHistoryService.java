package zyd.datacenter.Service.Game;

import zyd.datacenter.Entities.Game.GameHistory;
import zyd.datacenter.Entities.Game.GameOverview;
import zyd.datacenter.Payload.Result;

import java.util.List;

public interface GameHistoryService {
    public List<GameHistory> getGameHistory(String userId);

    public GameOverview replay(String gameId);

    public Result addHistory(GameHistory gameHistory);

}
