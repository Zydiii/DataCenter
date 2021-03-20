package zyd.datacenter.Entities.User;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(value = "UserScore")
@ApiModel(value="UserScore", description="玩家获取分数记录，每次对局结束后会存储本次获得的分数")
public class UserScore {
    @Id
    private String id;

    @ApiModelProperty(value = "用户编号")
    private String userId; // 玩家编号

    @ApiModelProperty(value = "用户名")
    private String username; // 用户名

    @ApiModelProperty(value = "获得积分的时间")
    @Indexed(direction = IndexDirection.ASCENDING)
    private Date getScoreDate; // 获得积分的时间

    @ApiModelProperty(value = "获得积分的时间戳")
    private long timestamp;

    @ApiModelProperty(value = "获得的积分")
    private float score; // 获得的积分

    @Version
    private Long version;

    public UserScore(String username, float score) {
        this.username = username;
        this.score = score;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public Date getGetScoreDate() {
        return getScoreDate;
    }

    public void setGetScoreDate(Date getScoreDate) {
        this.getScoreDate = getScoreDate;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
