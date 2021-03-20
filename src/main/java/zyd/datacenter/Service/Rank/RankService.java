package zyd.datacenter.Service.Rank;

import com.mongodb.MongoCommandException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.transaction.annotation.Transactional;
import zyd.datacenter.Entities.Rank.Rank;
import zyd.datacenter.Entities.Rank.RankType;

import java.util.List;

public interface RankService {
    @Transactional
    @Retryable(value = MongoCommandException.class, maxAttempts = 10, backoff = @Backoff(delay = 100))
    public List<?> getRank(RankType rankType);

    @Transactional
    @Retryable(value = MongoCommandException.class, maxAttempts = 10, backoff = @Backoff(delay = 100))
    public void Rank(RankType rankType);

    @Transactional
    @Retryable(value = MongoCommandException.class, maxAttempts = 10, backoff = @Backoff(delay = 100))
    public void weekRankScheduler();

    @Transactional
    @Retryable(value = MongoCommandException.class, maxAttempts = 10, backoff = @Backoff(delay = 100))
    public void allRankScheduler();

    @Transactional
    @Retryable(value = MongoCommandException.class, maxAttempts = 10, backoff = @Backoff(delay = 100))
    public void dayRankScheduler();

    @Transactional
    @Retryable(value = MongoCommandException.class, maxAttempts = 10, backoff = @Backoff(delay = 100))
    public void monthRankScheduler();

    @Transactional
    @Retryable(value = MongoCommandException.class, maxAttempts = 10, backoff = @Backoff(delay = 100))
    public void test();
}
