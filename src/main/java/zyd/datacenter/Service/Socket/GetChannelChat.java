package zyd.datacenter.Service.Socket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zyd.datacenter.Entities.Chat.ChannelChat;
import zyd.datacenter.Entities.User.User;
import zyd.datacenter.Repository.Chat.ChannelChatRepository;
import zyd.datacenter.Repository.User.UserRepository;

@Service
public class GetChannelChat {
    @Autowired
    private ChannelChatRepository channelChatRepository;

    @Autowired
    private UserRepository userRepository;

    public ChannelChat getChannelChat(String username, String message)
    {
        User user = userRepository.findByUsername(username).get();

        ChannelChat channelChat = new ChannelChat(user.getId(), "0", message);
        channelChat.setAvatar(user.getAvatarBase());
        channelChat.setUsername(user.getUsername());

        channelChatRepository.save(channelChat);

        return channelChat;
    }
}
