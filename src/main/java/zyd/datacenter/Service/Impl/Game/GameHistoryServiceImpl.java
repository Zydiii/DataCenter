package zyd.datacenter.Service.Impl.Game;

import org.springframework.stereotype.Service;
import zyd.datacenter.Entities.Game.GameHistory;
import zyd.datacenter.Entities.Game.GameOverview;
import zyd.datacenter.Entities.User.User;
import zyd.datacenter.Entities.User.UserInRoom;
import zyd.datacenter.Payload.Result;
import zyd.datacenter.Repository.Game.GameHistoryRepository;
import zyd.datacenter.Repository.Game.GameOverviewRepository;
import zyd.datacenter.Service.Game.GameHistoryService;

import java.util.LinkedList;
import java.util.List;

@Service
public class GameHistoryServiceImpl implements GameHistoryService {
    private GameHistoryRepository gameHistoryRepository;

    private GameOverviewRepository gameOverviewRepository;

    public GameHistoryServiceImpl(GameHistoryRepository gameHistoryRepository, GameOverviewRepository gameOverviewRepository) {
        this.gameHistoryRepository = gameHistoryRepository;
        this.gameOverviewRepository = gameOverviewRepository;
    }

    public List<UserInRoom> getGameHistory(String userId){
        List<GameHistory> gameHistories = gameHistoryRepository.findAllByUserId(userId);
        List<UserInRoom> userInRooms = new LinkedList<UserInRoom>();
        for(int i = 0; i < gameHistories.size(); i++)
        {
            GameOverview gameOverview = gameOverviewRepository.getByGameId(gameHistories.get(i).getGameId());
            for(UserInRoom userInRoom : gameOverview.getUserInRoom()){
                if(userInRoom.getUserId().equals(userId)){
                    userInRooms.add(userInRoom);
                    break;
                }
            }
        }
        return userInRooms;
    }

    public GameOverview replay(String gameId){
        return gameOverviewRepository.getByGameId(gameId);
    }

    public Result addHistory(GameHistory gameHistory){
        gameHistoryRepository.insert(gameHistory);
        return new Result("创建成功", 1);
    }
}
