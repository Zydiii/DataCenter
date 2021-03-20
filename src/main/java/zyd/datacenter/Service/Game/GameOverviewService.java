package zyd.datacenter.Service.Game;

import com.mongodb.MongoCommandException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.transaction.annotation.Transactional;
import zyd.datacenter.Entities.Game.GameOverview;
import zyd.datacenter.Payload.Result;

import java.util.List;

public interface GameOverviewService {
    @Transactional
    @Retryable(value = MongoCommandException.class, maxAttempts = 10, backoff = @Backoff(delay = 100))
    public Result addGame(GameOverview gameOverview);

    @Transactional
    @Retryable(value = MongoCommandException.class, maxAttempts = 10, backoff = @Backoff(delay = 100))
    public List<GameOverview> getAllGameOverview();
}
