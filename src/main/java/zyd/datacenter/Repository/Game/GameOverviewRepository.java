package zyd.datacenter.Repository.Game;

import org.springframework.data.mongodb.repository.MongoRepository;
import zyd.datacenter.Entities.Game.GameOverview;

import java.util.List;

public interface GameOverviewRepository extends MongoRepository<GameOverview, String> {
    GameOverview getByGameId(String gameId);
}
