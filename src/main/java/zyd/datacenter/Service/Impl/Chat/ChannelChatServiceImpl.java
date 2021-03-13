package zyd.datacenter.Service.Impl.Chat;

import org.springframework.stereotype.Service;
import zyd.datacenter.Entities.Chat.Channel;
import zyd.datacenter.Entities.Chat.ChannelChat;
import zyd.datacenter.Entities.User.User;
import zyd.datacenter.Payload.Result;
import zyd.datacenter.Repository.Chat.ChannelChatRepository;
import zyd.datacenter.Repository.Chat.ChannelRepository;
import zyd.datacenter.Repository.User.UserRepository;
import zyd.datacenter.Service.Chat.ChannelChatService;

import java.util.Date;
import java.util.List;

@Service
public class ChannelChatServiceImpl implements ChannelChatService {
    private ChannelChatRepository channelChatRepository;

    private UserRepository userRepository;

    private ChannelRepository channelRepository;

    public ChannelChatServiceImpl(ChannelChatRepository channelChatRepository, UserRepository userRepository, ChannelRepository channelRepository) {
        this.channelChatRepository = channelChatRepository;
        this.userRepository = userRepository;
        this.channelRepository = channelRepository;
    }

    public Result sendMessage(ChannelChat channelChat){
        channelChat.setDate(new Date());
        User user = userRepository.findById(channelChat.getUserId()).get();
        channelChat.setUsername(user.getUsername());
        Channel channel = channelRepository.findByChannelName(channelChat.getChannelName());
        channelChat.setChannelId(channel.getId());
        channelChatRepository.insert(channelChat);
        return new Result("消息发送成功", 1);
    }

    public List<ChannelChat> getMessage(String channelId){
        return channelChatRepository.getAllByChannelId(channelId);
    }

    public List<ChannelChat> getMessageByTime(ChannelChat channelChat){
        return channelChatRepository.getAllByChannelNameAndTimeStampGreaterThan(channelChat.getChannelName(),channelChat.getTimeStamp());
    }
}
