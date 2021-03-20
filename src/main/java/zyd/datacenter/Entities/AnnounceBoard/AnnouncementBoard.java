package zyd.datacenter.Entities.AnnounceBoard;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@Document(collection = "AnnouncementBoard")
@ApiModel(value="AnnouncementBoard", description="公告栏数据，存储管理员发布的公告栏")
public class AnnouncementBoard {
    @Id
    private String id;

    @ApiModelProperty(value = "公告信息文本")
    private String text; // 公告信息

    @ApiModelProperty(value = "发布者用户id")
    private String userId; // 发布者用户id

    @ApiModelProperty(value = "发布时间")
    private Date date; // 发布时间

    @ApiModelProperty(value = "发布的北京时间")
    private String bjDate; // 北京时间

    @ApiModelProperty(value = "发布的时间戳")
    @Indexed(direction = IndexDirection.DESCENDING)
    private long timeStamp; // 时间戳

    @Version
    private Long version;

    public AnnouncementBoard(String id, String text, String userId, Date date, String bjDate, long timeStamp) {
        this.id = id;
        this.text = text;
        this.userId = userId;
        this.date = date;
        this.bjDate = bjDate;
        this.timeStamp = timeStamp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
        SimpleDateFormat bjSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");     // 北京
        bjSdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));  // 设置北京时区
        this.bjDate = bjSdf.format(date);
        this.timeStamp = date.getTime();
    }

    public String getBjDate() {
        return bjDate;
    }

    public void setBjDate(String bjDate) {
        this.bjDate = bjDate;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }
}
