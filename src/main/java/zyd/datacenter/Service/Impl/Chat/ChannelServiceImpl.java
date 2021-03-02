package zyd.datacenter.Service.Impl.Chat;

import org.springframework.stereotype.Service;
import zyd.datacenter.Entities.Chat.Channel;
import zyd.datacenter.Payload.Result;
import zyd.datacenter.Repository.Chat.ChannelRepository;
import zyd.datacenter.Service.Chat.ChannelService;

import java.util.List;

@Service
public class ChannelServiceImpl implements ChannelService {
    private ChannelRepository channelRepository;

    public ChannelServiceImpl(ChannelRepository channelRepository) {
        this.channelRepository = channelRepository;
    }

    public List<Channel> getChannels(){
        return channelRepository.findAll();
    }

    public Result createChannel(String channelName){
        if(channelRepository.existsByChannelName(channelName)){
            return new Result("频道名已存在", 0);
        }
        else
        {
            Channel channel = new Channel(channelName);
            channelRepository.insert(channel);
            return new Result("频道创建成功!", 1);
        }
    }
}
