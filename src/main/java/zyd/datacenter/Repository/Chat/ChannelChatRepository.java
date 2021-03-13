package zyd.datacenter.Repository.Chat;

import org.springframework.data.mongodb.repository.MongoRepository;
import zyd.datacenter.Entities.Chat.ChannelChat;

import java.util.List;

public interface ChannelChatRepository extends MongoRepository<ChannelChat, String> {
    List<ChannelChat> getAllByChannelId(String channelId);

    List<ChannelChat> getAllByTimeStampGreaterThan(long timestamp);

    List<ChannelChat> getAllByChannelNameAndTimeStampGreaterThan(String channelName, long timestamp);
}
