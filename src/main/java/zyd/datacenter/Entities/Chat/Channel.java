package zyd.datacenter.Entities.Chat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "Channel")
@ApiModel(value="Channel", description="公共聊天频道，存储公共聊天频道，用户可以在不同频道发言")
public class Channel {
    @Id
    private String id;

    @ApiModelProperty(value = "频道名称")
    private String channelName; // 频道名称

    @Version
    private Long version;

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
