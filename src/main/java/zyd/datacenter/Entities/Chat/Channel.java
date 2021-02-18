package zyd.datacenter.Entities.Chat;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "Channel")
public class Channel {
    @Id
    private String id;

    private String channelName; // 频道名称

    public Channel(String channelName) {
        this.channelName = channelName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }
}
