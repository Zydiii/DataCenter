package zyd.datacenter.Service.Impl.Chat;

import org.springframework.stereotype.Service;
import zyd.datacenter.Entities.Chat.ChannelChat;
import zyd.datacenter.Payload.Result;
import zyd.datacenter.Repository.Chat.ChannelChatRepository;
import zyd.datacenter.Service.Chat.ChannelChatService;

import java.util.Date;
import java.util.List;

@Service
public class ChannelChatServiceImpl implements ChannelChatService {
    private ChannelChatRepository channelChatRepository;

    public ChannelChatServiceImpl(ChannelChatRepository channelChatRepository) {
        this.channelChatRepository = channelChatRepository;
    }

    public Result sendMessage(ChannelChat channelChat){
        channelChat.setDate(new Date());
        channelChatRepository.insert(channelChat);
        return new Result("消息发送成功", 1);
    }

    public List<ChannelChat> getMessage(String channelId){
        return channelChatRepository.getAllByChannelId(channelId);
    }
}
