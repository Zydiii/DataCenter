package zyd.datacenter.Service.AnnounceBoard;

import com.mongodb.MongoCommandException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.transaction.annotation.Transactional;
import zyd.datacenter.Entities.AnnounceBoard.AnnouncementBoard;
import zyd.datacenter.Payload.Result;

public interface AnnounceBoardService {
    @Transactional
    @Retryable(value = MongoCommandException.class, maxAttempts = 10, backoff = @Backoff(delay = 100))
    public Result writeAnnounce(AnnouncementBoard announceBoard);

    @Transactional
    @Retryable(value = MongoCommandException.class, maxAttempts = 10, backoff = @Backoff(delay = 100))
    public AnnouncementBoard getLatestAnnounce();
}
