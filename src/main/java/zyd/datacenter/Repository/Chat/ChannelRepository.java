package zyd.datacenter.Repository.Chat;

import org.springframework.data.mongodb.repository.MongoRepository;
import zyd.datacenter.Entities.Chat.Channel;

public interface ChannelRepository extends MongoRepository<Channel, String> {
    boolean existsByChannelName(String channelName);
}
