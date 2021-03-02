package zyd.datacenter.Service.Chat;

import zyd.datacenter.Entities.Chat.Channel;
import zyd.datacenter.Payload.Result;

import java.util.List;

public interface ChannelService {
    public List<Channel> getChannels();
    public Result createChannel(String channelName);
}
