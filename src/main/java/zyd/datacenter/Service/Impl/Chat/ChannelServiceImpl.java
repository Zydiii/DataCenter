package zyd.datacenter.Service.Impl.Chat;

import org.springframework.stereotype.Service;
import zyd.datacenter.Repository.Chat.ChannelRepository;
import zyd.datacenter.Service.Chat.ChannelService;

@Service
public class ChannelServiceImpl implements ChannelService {
    private ChannelRepository channelRepository;

    public ChannelServiceImpl(ChannelRepository channelRepository) {
        this.channelRepository = channelRepository;
    }
}
