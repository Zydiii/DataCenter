package zyd.datacenter.Service.Chat;

import zyd.datacenter.Entities.Chat.ChannelChat;
import zyd.datacenter.Payload.Result;

import java.util.List;

public interface ChannelChatService {
    public Result sendMessage(ChannelChat channelChat);

    public List<ChannelChat> getMessage(String channelId);

    public List<ChannelChat> getMessageByTime(ChannelChat channelChat);
}
