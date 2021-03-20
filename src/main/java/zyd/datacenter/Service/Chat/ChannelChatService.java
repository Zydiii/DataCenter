package zyd.datacenter.Service.Chat;

import com.mongodb.MongoCommandException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.transaction.annotation.Transactional;
import zyd.datacenter.Entities.Chat.ChannelChat;
import zyd.datacenter.Payload.Result;

import java.util.List;

public interface ChannelChatService {
    @Transactional
    @Retryable(value = MongoCommandException.class, maxAttempts = 10, backoff = @Backoff(delay = 100))
    public Result sendMessage(ChannelChat channelChat);

    @Transactional
    @Retryable(value = MongoCommandException.class, maxAttempts = 10, backoff = @Backoff(delay = 100))
    public List<ChannelChat> getMessage(String channelId);

    @Transactional
    @Retryable(value = MongoCommandException.class, maxAttempts = 10, backoff = @Backoff(delay = 100))
    public List<ChannelChat> getMessageByTime(ChannelChat channelChat);
}
