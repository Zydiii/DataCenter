package zyd.datacenter.Service.Game;

import zyd.datacenter.Entities.Game.GameHistory;
import zyd.datacenter.Entities.Game.GameOverview;
import zyd.datacenter.Entities.User.UserInRoom;
import zyd.datacenter.Payload.Result;

import java.util.List;

public interface GameHistoryService {
    public List<UserInRoom> getGameHistory(String userId);

    public GameOverview replay(String gameId);

    public Result addHistory(GameHistory gameHistory);

}
