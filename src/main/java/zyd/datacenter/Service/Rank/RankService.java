package zyd.datacenter.Service.Rank;

import com.mongodb.MongoCommandException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.transaction.annotation.Transactional;
import zyd.datacenter.Entities.Rank.Rank;
import zyd.datacenter.Entities.Rank.RankType;

import java.util.List;

public interface RankService {
    public List<?> getRank(RankType rankType);

    public void Rank(RankType rankType);

    public void weekRankScheduler();

    public void allRankScheduler();

    public void dayRankScheduler();

    public void monthRankScheduler();

    @Transactional
    @Retryable(value = MongoCommandException.class, maxAttempts = 2, backoff = @Backoff(delay = 100))
    public void test();
}
