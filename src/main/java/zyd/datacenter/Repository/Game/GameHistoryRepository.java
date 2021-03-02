package zyd.datacenter.Repository.Game;

import org.springframework.data.mongodb.repository.MongoRepository;
import zyd.datacenter.Entities.Game.GameHistory;
import zyd.datacenter.Payload.Result;

import java.util.List;

public interface GameHistoryRepository extends MongoRepository<GameHistory, String> {
    List<GameHistory> findAllByUserId(String userId);
}
