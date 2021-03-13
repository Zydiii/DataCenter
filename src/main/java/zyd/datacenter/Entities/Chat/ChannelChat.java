package zyd.datacenter.Entities.Chat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import zyd.datacenter.Entities.User.User;
import zyd.datacenter.Repository.User.UserRepository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@Document(value = "ChannelChat")
public class ChannelChat {
    @Id
    private String id;

    private String userId; // 用户编号

    private String username; // 用户名

    private String channelId; // 频道编号

    private String channelName; // 频道名称

    private String chat; // 聊天内容

    private Date date; // 发言时间

    private long timeStamp; // 发言时间戳

    private String bjDate; // 发言北京时间

    public ChannelChat(String userId, String channelId, String chat) {
        this.userId = userId;
        this.channelId = channelId;
        this.chat = chat;
        this.date = new Date();
        this.timeStamp = this.date.getTime();
        SimpleDateFormat bjSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");     // 北京
        bjSdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));  // 设置北京时区
        this.bjDate = bjSdf.format(this.date);
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getBjDate() {
        return bjDate;
    }

    public void setBjDate(String bjDate) {
        this.bjDate = bjDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getChat() {
        return chat;
    }

    public void setChat(String chat) {
        this.chat = chat;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
        this.timeStamp = date.getTime();
        SimpleDateFormat bjSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");     // 北京
        bjSdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));  // 设置北京时区
        this.bjDate = bjSdf.format(date);
    }
}
